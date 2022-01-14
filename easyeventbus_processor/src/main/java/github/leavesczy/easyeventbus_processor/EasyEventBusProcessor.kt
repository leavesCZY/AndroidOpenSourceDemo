package github.leavesczy.easyeventbus_processor

import com.squareup.javapoet.*
import com.squareup.javapoet.ParameterizedTypeName
import com.squareup.javapoet.WildcardTypeName
import github.leavesczy.easyeventbus_api.Event
import github.leavesczy.easyeventbus_api.EventMethodInfo
import github.leavesczy.easyeventbus_api.SubscriberInfo
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Messager
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

/**
 * @Author: leavesCZY
 * @Date: 2020/10/3 15:55
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class EasyEventBusProcessor : AbstractProcessor() {

    companion object {

        private const val PACKAGE_NAME = "github.leavesczy.easyeventbus"

        private const val CLASS_NAME = "EventBusInject"

        private const val DOC = "这是自动生成的代码 by leavesCZY"

    }

    private lateinit var elementUtils: Elements

    private val methodsByClass = LinkedHashMap<TypeElement, MutableList<ExecutableElement>>()

    override fun init(processingEnvironment: ProcessingEnvironment) {
        super.init(processingEnvironment)
        elementUtils = processingEnv.elementUtils
    }

    private fun getClassAny(): TypeName {
        return ParameterizedTypeName.get(
            ClassName.get(Class::class.java),
            WildcardTypeName.subtypeOf(Any::class.java)
        )
    }

    //生成 subscriberIndex 这个静态常量
    private fun generateSubscriberField(): FieldSpec {
        val subscriberIndex = ParameterizedTypeName.get(
            ClassName.get(Map::class.java),
            getClassAny(),
            ClassName.get(SubscriberInfo::class.java)
        )
        return FieldSpec.builder(subscriberIndex, "subscriberIndex")
            .addModifiers(
                Modifier.PRIVATE,
                Modifier.STATIC,
                Modifier.FINAL
            )
            .initializer(
                "new ${"$"}T<Class<?>, ${"$"}T>()",
                HashMap::class.java,
                SubscriberInfo::class.java
            )
            .build()
    }

    //生成静态方法块
    private fun generateInitializerBlock(builder: TypeSpec.Builder) {
        for (item in methodsByClass) {
            val methods = item.value
            if (methods.isEmpty()) {
                break
            }
            val codeBuilder = CodeBlock.builder()
            codeBuilder.add(
                "${"$"}T<${"$"}T> eventMethodInfoList = new ${"$"}T<${"$"}T>();",
                List::class.java,
                EventMethodInfo::class.java,
                ArrayList::class.java,
                EventMethodInfo::class.java
            )
            methods.forEach {
                val methodName = it.simpleName.toString()
                val eventType = it.parameters[0].asType()
                codeBuilder.add(
                    "eventMethodInfoList.add(new EventMethodInfo(${"$"}S, ${"$"}T.class));",
                    methodName,
                    eventType
                )
            }
            codeBuilder.add(
                "SubscriberInfo subscriberInfo = new SubscriberInfo(${"$"}T.class, eventMethodInfoList); putIndex(subscriberInfo);",
                item.key.asType()
            )
            builder.addInitializerBlock(
                codeBuilder.build()
            )
        }
    }

    //生成 putIndex 方法
    private fun generateMethodPutIndex(): MethodSpec {
        return MethodSpec.methodBuilder("putIndex")
            .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
            .returns(Void.TYPE)
            .addParameter(SubscriberInfo::class.java, "info")
            .addCode(
                CodeBlock.builder().add("subscriberIndex.put(info.getSubscriberClass() , info);")
                    .build()
            )
            .build()
    }

    //生成 getSubscriberInfo 方法
    private fun generateMethodGetSubscriberInfo(): MethodSpec {
        return MethodSpec.methodBuilder("getSubscriberInfo")
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .returns(SubscriberInfo::class.java)
            .addParameter(getClassAny(), "subscriberClass")
            .addCode(
                CodeBlock.builder().add("return subscriberIndex.get(subscriberClass);")
                    .build()
            )
            .build()
    }

    override fun process(
        set: Set<TypeElement>,
        roundEnvironment: RoundEnvironment
    ): Boolean {
        val messager = processingEnv.messager
        collectSubscribers(roundEnvironment, messager)
        if (methodsByClass.isEmpty()) {
            messager.printMessage(Diagnostic.Kind.WARNING, "No @Event annotations found")
        } else {
            val typeSpec = TypeSpec.classBuilder(CLASS_NAME)
                .addModifiers(Modifier.PUBLIC)
                .addJavadoc(DOC)
                .addField(generateSubscriberField())
                .addMethod(generateMethodPutIndex())
                .addMethod(generateMethodGetSubscriberInfo())
            generateInitializerBlock(typeSpec)
            val javaFile = JavaFile.builder(PACKAGE_NAME, typeSpec.build())
                .build()
            try {
                javaFile.writeTo(processingEnv.filer)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        return true
    }

    private fun collectSubscribers(
        roundEnvironment: RoundEnvironment,
        messager: Messager
    ) {
        val elements = roundEnvironment.getElementsAnnotatedWith(Event::class.java)
        if (elements.isNullOrEmpty()) {
            return
        }
        for (element in elements) {
            if (element is ExecutableElement) {
                if (checkHasNoErrors(element, messager)) {
                    val classElement = element.enclosingElement as TypeElement
                    var list = methodsByClass[classElement]
                    if (list == null) {
                        list = mutableListOf()
                        methodsByClass[classElement] = list
                    }
                    list.add(element)
                }
            } else {
                //@Event 只能用于修改方法
                messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "@Event is only valid for methods",
                    element
                )
            }
        }
    }

    /**
     * 校验方法签名是否合法
     */
    private fun checkHasNoErrors(element: ExecutableElement, messager: Messager): Boolean {
        //不能是静态方法
        if (element.modifiers.contains(Modifier.STATIC)) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Event method must not be static", element)
            return false
        }
        //必须是 public 方法
        if (!element.modifiers.contains(Modifier.PUBLIC)) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Event method must be public", element)
            return false
        }
        //方法只能且最多包含一个参数
        val parameters = element.parameters
        if (parameters.size != 1) {
            messager.printMessage(
                Diagnostic.Kind.ERROR,
                "Event method must have exactly 1 parameter",
                element
            )
            return false
        }
        return true
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(Event::class.java.canonicalName)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.RELEASE_8
    }

}
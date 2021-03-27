package github.leavesc.easyrouter_processor

import com.squareup.javapoet.*
import github.leavesc.easyrouter_annotation.Router
import github.leavesc.easyrouter_annotation.RouterBean
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Messager
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

/**
 * @Author: leavesC
 * @Date: 2020/10/5 22:17
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class EasyRouterProcessor : AbstractProcessor() {

    companion object {

        private const val KEY_MODULE_NAME = "EASYROUTER_MODULE_NAME"

        private const val PACKAGE_NAME = "github.leavesc.easyrouter"

        private const val DOC = "这是自动生成的代码 by leavesC"

    }

    private lateinit var elementUtils: Elements

    private lateinit var messager: Messager

    private lateinit var moduleName: String

    override fun init(processingEnvironment: ProcessingEnvironment) {
        super.init(processingEnvironment)
        elementUtils = processingEnv.elementUtils
        messager = processingEnv.messager
        val options = processingEnv.options
        moduleName = options[KEY_MODULE_NAME] ?: ""
        if (moduleName.isBlank()) {
            messager.printMessage(Diagnostic.Kind.ERROR, "$KEY_MODULE_NAME must not be null")
        }
    }

    //生成 routerMap 这个静态常量
    private fun generateSubscriberField(): FieldSpec {
        val subscriberIndex = ParameterizedTypeName.get(
            ClassName.get(Map::class.java),
            ClassName.get(String::class.java),
            ClassName.get(RouterBean::class.java)
        )
        return FieldSpec.builder(subscriberIndex, "routerMap")
            .addModifiers(
                Modifier.PUBLIC,
                Modifier.STATIC,
                Modifier.FINAL
            )
            .initializer("new ${"$"}T<>()", HashMap::class.java)
            .build()
    }

    //生成静态方法块
    private fun generateInitializerBlock(
        elements: MutableSet<out Element>,
        builder: TypeSpec.Builder
    ) {
        val codeBuilder = CodeBlock.builder()
        elements.forEach {
            val router = it.getAnnotation(Router::class.java)
            val path = router.path
            val group = path.substring(0, path.indexOf("/"))
            codeBuilder.add(
                "routerMap.put(${"$"}S, new ${"$"}T(${"$"}T.class, ${"$"}S, ${"$"}S));",
                path,
                RouterBean::class.java,
                it.asType(),
                path,
                group
            )
        }
        builder.addInitializerBlock(
            codeBuilder.build()
        )
    }

    override fun process(
        mutableSet: MutableSet<out TypeElement>,
        roundEnvironment: RoundEnvironment
    ): Boolean {
        val elements: MutableSet<out Element> =
            roundEnvironment.getElementsAnnotatedWith(Router::class.java)
        if (elements.isNullOrEmpty()) {
            return true
        }
        val typeSpec = TypeSpec.classBuilder("EasyRouter" + moduleName + "Loader")
            .addModifiers(Modifier.PUBLIC)
            .addField(generateSubscriberField())
            .addJavadoc(DOC)
        generateInitializerBlock(elements, typeSpec)
        val javaFile = JavaFile.builder(PACKAGE_NAME, typeSpec.build())
            .build()
        try {
            javaFile.writeTo(processingEnv.filer)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return true
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(Router::class.java.canonicalName)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.RELEASE_8
    }

    override fun getSupportedOptions(): Set<String> {
        return hashSetOf(KEY_MODULE_NAME)
    }

}
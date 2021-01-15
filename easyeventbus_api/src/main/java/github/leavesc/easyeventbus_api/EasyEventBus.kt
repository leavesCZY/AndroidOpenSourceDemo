package github.leavesc.easyeventbus_api

/**
 * 作者：leavesC
 * 时间：2020/10/3 11:44
 * 描述：
 * GitHub：https://github.com/leavesC
 */
object EasyEventBus {

    private val subscriptions = mutableSetOf<Any>()

    private const val PACKAGE_NAME = "github.leavesc.easyeventbus"

    private const val CLASS_NAME = "EventBusInject"

    private const val CLASS_PATH = "$PACKAGE_NAME.$CLASS_NAME"

    private val clazz = Class.forName(CLASS_PATH)

    //通过反射生成 EventBusInject 对象
    private val instance = clazz.newInstance()

    @Synchronized
    fun register(subscriber: Any) {
        subscriptions.add(subscriber)
    }

    @Synchronized
    fun unregister(subscriber: Any) {
        subscriptions.remove(subscriber)
    }

    @Synchronized
    fun post(event: Any) {
        subscriptions.forEach { subscriber ->
            val subscriberInfo =
                getSubscriberInfo(subscriber.javaClass)
            if (subscriberInfo != null) {
                val methodList = subscriberInfo.methodList
                methodList.forEach { method ->
                    if (method.eventType == event.javaClass) {
                        val declaredMethod = subscriber.javaClass.getDeclaredMethod(
                            method.methodName,
                            method.eventType
                        )
                        declaredMethod.invoke(subscriber, event)
                    }
                }
            }
        }
    }

    //通过反射调用 EventBusInject 的 getSubscriberInfo 方法
    private fun getSubscriberInfo(subscriberClass: Class<*>): SubscriberInfo? {
        val method = clazz.getMethod("getSubscriberInfo", Class::class.java)
        return method.invoke(instance, subscriberClass) as? SubscriberInfo
    }

}
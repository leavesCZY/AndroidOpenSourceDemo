package github.leavesc.easyeventbus_api

/**
 * 作者：leavesC
 * 时间：2020/10/3 17:33
 * 描述：
 * GitHub：https://github.com/leavesC
 */
data class EventMethodInfo(val methodName: String, val eventType: Class<*>)

data class SubscriberInfo(
    val subscriberClass: Class<*>,
    val methodList: List<EventMethodInfo>
)
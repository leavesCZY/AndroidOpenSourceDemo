package github.leavesc.easyeventbus_api

/**
 * @Author: leavesC
 * @Date: 2020/10/3 17:33
 * @Desc:
 * @Github：https://github.com/leavesC
 */
data class EventMethodInfo(val methodName: String, val eventType: Class<*>)

data class SubscriberInfo(
    val subscriberClass: Class<*>,
    val methodList: List<EventMethodInfo>
)
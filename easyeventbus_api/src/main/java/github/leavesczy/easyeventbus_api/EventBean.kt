package github.leavesczy.easyeventbus_api

/**
 * @Author: leavesCZY
 * @Date: 2020/10/3 17:33
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
data class EventMethodInfo(val methodName: String, val eventType: Class<*>)

data class SubscriberInfo(
    val subscriberClass: Class<*>,
    val methodList: List<EventMethodInfo>
)
package github.leavesczy.retrofit.http

import java.io.IOException
import java.net.SocketException

/**
 * @Author: leavesCZY
 * @Date: 2020/10/24 10:30
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
sealed class BaseHttpException(
    val errorCode: Int,
    val errorMessage: String,
    val realException: Throwable?
) : Exception(errorMessage) {

    companion object {

        const val CODE_UNKNOWN = -1024

        const val CODE_NETWORK_BAD = -1025

        fun generateException(throwable: Throwable?): BaseHttpException {
            return when (throwable) {
                is BaseHttpException -> {
                    throwable
                }
                is SocketException, is IOException -> {
                    NetworkBadException(
                        "网络请求失败",
                        throwable
                    )
                }
                else -> {
                    UnknownException(
                        "未知错误",
                        throwable
                    )
                }
            }
        }

    }

}

/**
 * 由于网络原因导致 API 请求失败
 * @param errorMessage
 * @param realException
 */
class NetworkBadException(errorMessage: String, realException: Throwable) :
    BaseHttpException(CODE_NETWORK_BAD, errorMessage, realException)

/**
 * API 请求成功了，但 code != successCode
 * @param bean
 */
class ServerCodeNoSuccessException(bean: HttpWrapBean<*>) :
    BaseHttpException(bean.status, bean.msg, null)

/**
 * 未知错误
 * @param errorMessage
 * @param realException
 */
class UnknownException(errorMessage: String, realException: Throwable?) :
    BaseHttpException(CODE_UNKNOWN, errorMessage, realException)
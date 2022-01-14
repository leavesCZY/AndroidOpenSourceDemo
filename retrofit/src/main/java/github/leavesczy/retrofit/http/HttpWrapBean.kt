package github.leavesczy.retrofit.http

/**
 * @Author: leavesCZY
 * @Date: 2020/10/24 10:29
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
data class HttpWrapBean<T>(val status: Int, val msg: String, val data: T) {

    companion object {

        fun error(throwable: Throwable): HttpWrapBean<*> {
            val exception = BaseHttpException.generateException(
                throwable
            )
            return HttpWrapBean(
                exception.errorCode,
                exception.errorMessage,
                null
            )
        }

    }

    val isSuccess: Boolean
        get() = status == 200

}
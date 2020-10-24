package github.leavesc.retrofit.http

/**
 * 作者：leavesC
 * 时间：2020/10/24 10:29
 * 描述：
 * GitHub：https://github.com/leavesC
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
package github.leavesczy.glide.net

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Author: leavesCZY
 * @Date: 2020/11/6 22:08
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class ProgressInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalResponse = chain.proceed(request)
        val url = request.url.toString()
        return originalResponse.newBuilder()
            .body(ProgressResponseBody(url, originalResponse.body))
            .build()
    }

}
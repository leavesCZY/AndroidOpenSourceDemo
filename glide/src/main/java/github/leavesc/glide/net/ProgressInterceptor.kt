package github.leavesc.glide.net

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Author: leavesC
 * @Date: 2020/11/6 22:08
 * @Desc:
 * @Github：https://github.com/leavesC
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
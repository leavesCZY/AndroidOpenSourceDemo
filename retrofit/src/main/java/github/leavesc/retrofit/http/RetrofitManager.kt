package github.leavesc.retrofit.http

import android.content.Context
import github.leavesc.monitor.MonitorInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Author: leavesC
 * @Date: 2020/10/22 20:47
 * @Desc:
 * @Github：https://github.com/leavesC
 */
object RetrofitManager {

    lateinit var context: Context

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(1000L, TimeUnit.MILLISECONDS)
            .writeTimeout(1000L, TimeUnit.MILLISECONDS)
            .connectTimeout(1000L, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(MonitorInterceptor(context)).build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://getman.cn/mock/")
            .callFactory(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .build()
    }

    internal val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}
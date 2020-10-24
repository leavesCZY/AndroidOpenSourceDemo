package github.leavesc.demo

import android.app.Application
import github.leavesc.easyrouter.EasyRouter
import github.leavesc.retrofit.http.RetrofitManager

/**
 * 作者：leavesC
 * 时间：2020/10/2 13:19
 * 描述：
 * GitHub：https://github.com/leavesC
 */
class MyApp : Application() {

    companion object {

        lateinit var context: Application

    }

    override fun onCreate() {
        super.onCreate()
        context = this
        RetrofitManager.context = context
        EasyRouter.init(this)
    }

}
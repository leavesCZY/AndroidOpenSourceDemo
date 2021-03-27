package github.leavesc.demo

import android.app.Application
import github.leavesc.base.ContextHolder
import github.leavesc.coil.CoilHolder
import github.leavesc.easyrouter_api.EasyRouter
import github.leavesc.retrofit.http.RetrofitManager

/**
 * @Author: leavesC
 * @Date: 2020/10/2 13:19
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class MyApp : Application() {

    companion object {

        lateinit var context: Application

    }

    override fun onCreate() {
        super.onCreate()
        context = this
        ContextHolder.context = this
        RetrofitManager.context = context
        EasyRouter.init(this)
        CoilHolder.init(this)
    }

}
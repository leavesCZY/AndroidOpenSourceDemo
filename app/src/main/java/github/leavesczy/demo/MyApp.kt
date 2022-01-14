package github.leavesczy.demo

import android.app.Application
import github.leavesczy.base.ContextHolder
import github.leavesczy.coil.CoilHolder
import github.leavesczy.easyrouter_api.EasyRouter
import github.leavesczy.retrofit.http.RetrofitManager

/**
 * @Author: leavesCZY
 * @Date: 2020/10/2 13:19
 * @Desc:
 * @Github：https://github.com/leavesCZY
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
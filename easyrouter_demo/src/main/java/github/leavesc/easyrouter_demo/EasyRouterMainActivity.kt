package github.leavesc.easyrouter_demo

import android.os.Bundle
import github.leavesc.base.BaseActivity
import github.leavesc.base.EasyRouterPath
import github.leavesc.easyrouter_annotation.Router
import github.leavesc.easyrouter_api.EasyRouter
import github.leavesc.easyrouter_demo.databinding.ActivityEasyRouterMainBinding

/**
 * 作者：leavesC
 * 时间：2021/1/15 23:53
 * 描述：
 * GitHub：https://github.com/leavesC
 */
@Router(EasyRouterPath.PATH_EASY_ROUTER_MAIN)
class EasyRouterMainActivity : BaseActivity() {

    override val bind by getBind<ActivityEasyRouterMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.btnNavToSubPage.setOnClickListener {
            EasyRouter.navigation(EasyRouterPath.PATH_EASY_ROUTER_SUB_PAGE)
        }
        bind.btnNavToAppMainPage.setOnClickListener {
            EasyRouter.navigation(EasyRouterPath.PATH_EASY_ROUTER_APP_MAIN_PAGE)
        }
        bind.btnNavToNothing.setOnClickListener {
            EasyRouter.navigation("test/nothing")
        }
    }

}
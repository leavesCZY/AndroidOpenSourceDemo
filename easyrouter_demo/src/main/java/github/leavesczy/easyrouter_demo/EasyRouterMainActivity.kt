package github.leavesczy.easyrouter_demo

import android.os.Bundle
import github.leavesczy.base.BaseActivity
import github.leavesczy.base.EasyRouterPath
import github.leavesczy.easyrouter_annotation.Router
import github.leavesczy.easyrouter_api.EasyRouter
import github.leavesczy.easyrouter_demo.databinding.ActivityEasyRouterMainBinding

/**
 * @Author: leavesCZY
 * @Date: 2021/1/15 23:53
 * @Desc:
 * @Github：https://github.com/leavesCZY
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
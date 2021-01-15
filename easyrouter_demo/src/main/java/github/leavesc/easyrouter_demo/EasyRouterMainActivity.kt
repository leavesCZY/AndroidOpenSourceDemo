package github.leavesc.easyrouter_demo

import android.os.Bundle
import github.leavesc.base.BaseActivity
import github.leavesc.base.EasyRouterPath
import github.leavesc.easyrouter_annotation.Router
import github.leavesc.easyrouter_api.EasyRouter
import kotlinx.android.synthetic.main.activity_easy_router_main.*

/**
 * 作者：leavesC
 * 时间：2021/1/15 23:53
 * 描述：
 * GitHub：https://github.com/leavesC
 */
@Router(EasyRouterPath.PATH_EASY_ROUTER_MAIN)
class EasyRouterMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_router_main)
        btn_navToSubPage.setOnClickListener {
            EasyRouter.navigation(EasyRouterPath.PATH_EASY_ROUTER_SUB_PAGE)
        }
        btn_navToAppMainPage.setOnClickListener {
            EasyRouter.navigation(EasyRouterPath.PATH_EASY_ROUTER_APP_MAIN_PAGE)
        }
        btn_navToNothing.setOnClickListener {
            EasyRouter.navigation("test/nothing")
        }
    }

}
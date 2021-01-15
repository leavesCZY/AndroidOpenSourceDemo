package github.leavesc.easyrouter_demo

import android.os.Bundle
import github.leavesc.base.BaseActivity
import github.leavesc.base.EasyRouterPath
import github.leavesc.easyrouter_annotation.Router

/**
 * 作者：leavesC
 * 时间：2021/1/16 0:03
 * 描述：
 * GitHub：https://github.com/leavesC
 */
@Router(EasyRouterPath.PATH_EASY_ROUTER_SUB_PAGE)
class EasyRouterSubActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_router_sub)
    }

}
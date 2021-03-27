package github.leavesc.easyrouter_demo

import github.leavesc.base.BaseActivity
import github.leavesc.base.EasyRouterPath
import github.leavesc.easyrouter_annotation.Router
import github.leavesc.easyrouter_demo.databinding.ActivityEasyRouterSubBinding

/**
 * @Author: leavesC
 * @Date: 2021/1/16 0:03
 * @Desc:
 * @Github：https://github.com/leavesC
 */
@Router(EasyRouterPath.PATH_EASY_ROUTER_SUB_PAGE)
class EasyRouterSubActivity : BaseActivity() {

    override val bind by getBind<ActivityEasyRouterSubBinding>()

}
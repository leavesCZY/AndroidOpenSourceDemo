package github.leavesczy.easyrouter_demo

import github.leavesczy.base.BaseActivity
import github.leavesczy.base.EasyRouterPath
import github.leavesczy.easyrouter_annotation.Router
import github.leavesczy.easyrouter_demo.databinding.ActivityEasyRouterSubBinding

/**
 * @Author: leavesCZY
 * @Date: 2021/1/16 0:03
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
@Router(EasyRouterPath.PATH_EASY_ROUTER_SUB_PAGE)
class EasyRouterSubActivity : BaseActivity() {

    override val bind by getBind<ActivityEasyRouterSubBinding>()

}
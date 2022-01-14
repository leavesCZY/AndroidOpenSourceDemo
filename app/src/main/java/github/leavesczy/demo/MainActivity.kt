package github.leavesczy.demo

import android.os.Bundle
import github.leavesczy.base.BaseActivity
import github.leavesczy.base.EasyRouterPath
import github.leavesczy.coil.CoilMainActivity
import github.leavesczy.constraint_layout.ConstraintLayoutMainActivity
import github.leavesczy.customview.CustomViewMainActivity
import github.leavesczy.demo.databinding.ActivityMainBinding
import github.leavesczy.easyeventbus_demo.EasyEventBusMainActivity
import github.leavesczy.easyrouter_annotation.Router
import github.leavesczy.easyrouter_api.EasyRouter
import github.leavesczy.fragment.FragmentMainActivity
import github.leavesczy.glide.GlideActivity
import github.leavesczy.jetpack_compose.ComposeMainActivity
import github.leavesczy.launchmode.LaunchModeMainActivity
import github.leavesczy.motion_event.MotionEventMainActivity
import github.leavesczy.motion_layout.MotionLayoutMainActivity
import github.leavesczy.retrofit.LiveDataCallAdapterActivity

/**
 * @Author: leavesCZY
 * @Date: 2020/10/2 13:14
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
@Router(EasyRouterPath.PATH_EASY_ROUTER_APP_MAIN_PAGE)
class MainActivity : BaseActivity() {

    override val bind by getBind<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.btnEasyEventBus.setOnClickListener {
            startActivity<EasyEventBusMainActivity>()
        }
        bind.btnEasyRouter.setOnClickListener {
            EasyRouter.navigation(EasyRouterPath.PATH_EASY_ROUTER_MAIN)
        }
        bind.btnLiveDataCallAdapter.setOnClickListener {
            startActivity<LiveDataCallAdapterActivity>()
        }
        bind.btnGlide.setOnClickListener {
            startActivity<GlideActivity>()
        }
        bind.btnCoil.setOnClickListener {
            startActivity<CoilMainActivity>()
        }
        bind.btnConstraintLayout.setOnClickListener {
            startActivity<ConstraintLayoutMainActivity>()
        }
        bind.btnMotionLayout.setOnClickListener {
            startActivity<MotionLayoutMainActivity>()
        }
        bind.btnMotionEvent.setOnClickListener {
            startActivity<MotionEventMainActivity>()
        }
        bind.btnCustomView.setOnClickListener {
            startActivity<CustomViewMainActivity>()
        }
        bind.btnLaunchMode.setOnClickListener {
            startActivity<LaunchModeMainActivity>()
        }
        bind.btnJetpackCompose.setOnClickListener {
            startActivity<ComposeMainActivity>()
        }
        bind.btnFragment.setOnClickListener {
            startActivity<FragmentMainActivity>()
        }
    }

}
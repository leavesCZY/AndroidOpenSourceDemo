package github.leavesc.demo

import android.os.Bundle
import github.leavesc.base.BaseActivity
import github.leavesc.base.EasyRouterPath
import github.leavesc.coil.CoilMainActivity
import github.leavesc.compose.ComposeMainActivity
import github.leavesc.constraint_layout.ConstraintLayoutMainActivity
import github.leavesc.customview.CustomViewMainActivity
import github.leavesc.demo.databinding.ActivityMainBinding
import github.leavesc.easyeventbus_demo.EasyEventBusMainActivity
import github.leavesc.easyrouter_annotation.Router
import github.leavesc.easyrouter_api.EasyRouter
import github.leavesc.glide.GlideActivity
import github.leavesc.launchmode.LaunchModeMainActivity
import github.leavesc.motion_event.MotionEventMainActivity
import github.leavesc.motion_layout.MotionLayoutMainActivity
import github.leavesc.retrofit.LiveDataCallAdapterActivity

/**
 * @Author: leavesC
 * @Date: 2020/10/2 13:14
 * @Desc:
 * @Github：https://github.com/leavesC
 */
@Router(EasyRouterPath.PATH_EASY_ROUTER_APP_MAIN_PAGE)
class MainActivity : BaseActivity() {

    override val bind by getBind<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.btnCompose.setOnClickListener {
            startActivity<ComposeMainActivity>()
        }
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
    }

}
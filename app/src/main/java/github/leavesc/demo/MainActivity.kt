package github.leavesc.demo

import android.os.Bundle
import github.leavesc.base.BaseActivity
import github.leavesc.base.EasyRouterPath
import github.leavesc.coil.CoilMainActivity
import github.leavesc.constraint_layout.ConstraintLayoutMainActivity
import github.leavesc.easyeventbus_demo.EasyEventBusMainActivity
import github.leavesc.easyrouter_annotation.Router
import github.leavesc.easyrouter_api.EasyRouter
import github.leavesc.glide.GlideActivity
import github.leavesc.motion_event.MotionEventMainActivity
import github.leavesc.motion_layout.MotionLayoutMainActivity
import github.leavesc.retrofit.LiveDataCallAdapterActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 作者：leavesC
 * 时间：2020/10/2 13:14
 * 描述：
 * GitHub：https://github.com/leavesC
 */
@Router(EasyRouterPath.PATH_EASY_ROUTER_APP_MAIN_PAGE)
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_easyEventBus.setOnClickListener {
            startActivity(EasyEventBusMainActivity::class.java)
        }
        btn_easyRouter.setOnClickListener {
            EasyRouter.navigation(EasyRouterPath.PATH_EASY_ROUTER_MAIN)
        }
        btn_liveDataCallAdapter.setOnClickListener {
            startActivity(LiveDataCallAdapterActivity::class.java)
        }
        btn_glide.setOnClickListener {
            startActivity(GlideActivity::class.java)
        }
        btn_coil.setOnClickListener {
            startActivity(CoilMainActivity::class.java)
        }
        btn_constraintLayout.setOnClickListener {
            startActivity(ConstraintLayoutMainActivity::class.java)
        }
        btn_motionLayout.setOnClickListener {
            startActivity(MotionLayoutMainActivity::class.java)
        }
        btn_motionEvent.setOnClickListener {
            startActivity(MotionEventMainActivity::class.java)
        }
    }

}
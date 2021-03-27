package github.leavesc.motion_event

import android.os.Bundle
import github.leavesc.base.BaseActivity
import github.leavesc.motion_event.databinding.ActivityMotionEventMainBinding

/**
 * @Author: leavesC
 * @Date: 2021/1/15 23:17
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class MotionEventMainActivity : BaseActivity() {

    override val bind by getBind<ActivityMotionEventMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.btnDemoA.setOnClickListener {
            startActivity(MotionEventDemoAActivity::class.java)
        }
        bind.btnDemoB.setOnClickListener {
            startActivity(MotionEventDemoBActivity::class.java)
        }
        bind.btnDemoC.setOnClickListener {
            startActivity(MotionEventDemoCActivity::class.java)
        }
    }

}
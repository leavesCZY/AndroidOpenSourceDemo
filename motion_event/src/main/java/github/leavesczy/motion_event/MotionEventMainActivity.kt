package github.leavesczy.motion_event

import android.os.Bundle
import github.leavesczy.base.BaseActivity
import github.leavesczy.motion_event.databinding.ActivityMotionEventMainBinding

/**
 * @Author: leavesCZY
 * @Date: 2021/1/15 23:17
 * @Desc:
 * @Github：https://github.com/leavesCZY
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
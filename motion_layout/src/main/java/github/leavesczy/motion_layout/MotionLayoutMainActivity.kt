package github.leavesczy.motion_layout

import android.os.Bundle
import github.leavesczy.base.BaseActivity
import github.leavesczy.motion_layout.databinding.ActivityMotionLayoutMainBinding

/**
 * @Author: leavesCZY
 * @Date: 2020/12/27 15:19
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class MotionLayoutMainActivity : BaseActivity() {

    override val bind by getBind<ActivityMotionLayoutMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.btnDemoA.setOnClickListener {
            startActivity(MotionLayoutDemoAActivity::class.java)
        }
        bind.btnDemoB.setOnClickListener {
            startActivity(MotionLayoutDemoBActivity::class.java)
        }
        bind.btnDemoC.setOnClickListener {
            startActivity(MotionLayoutDemoCActivity::class.java)
        }
        bind.btnDemoD.setOnClickListener {
            startActivity(MotionLayoutDemoDActivity::class.java)
        }
        bind.btnDemoE.setOnClickListener {
            startActivity(MotionLayoutDemoEActivity::class.java)
        }
    }

}
package github.leavesc.motion_layout

import android.os.Bundle
import github.leavesc.base.BaseActivity
import kotlinx.android.synthetic.main.activity_motion_layout_main.*

/**
 * @Author: leavesC
 * @Date: 2020/12/27 15:19
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class MotionLayoutMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_layout_main)
        btn_demoA.setOnClickListener {
            startActivity(MotionLayoutDemoAActivity::class.java)
        }
        btn_demoB.setOnClickListener {
            startActivity(MotionLayoutDemoBActivity::class.java)
        }
        btn_demoC.setOnClickListener {
            startActivity(MotionLayoutDemoCActivity::class.java)
        }
        btn_demoD.setOnClickListener {
            startActivity(MotionLayoutDemoDActivity::class.java)
        }
        btn_demoE.setOnClickListener {
            startActivity(MotionLayoutDemoEActivity::class.java)
        }
    }

}
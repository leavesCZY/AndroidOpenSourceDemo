package github.leavesc.motion_event

import android.os.Bundle
import github.leavesc.base.BaseActivity
import kotlinx.android.synthetic.main.activity_motion_event_main.*

/**
 * 作者：leavesC
 * 时间：2021/1/15 23:17
 * 描述：
 * GitHub：https://github.com/leavesC
 */
class MotionEventMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_event_main)
        btn_demoA.setOnClickListener {
            startActivity(MotionEventDemoAActivity::class.java)
        }
        btn_demoB.setOnClickListener {

        }
    }

}
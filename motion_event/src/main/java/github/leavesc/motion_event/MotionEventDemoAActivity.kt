package github.leavesc.motion_event

import android.os.Bundle
import android.view.MotionEvent
import github.leavesc.base.BaseActivity

/**
 * 作者：leavesC
 * 时间：2021/1/16 0:34
 * 描述：
 * GitHub：https://github.com/leavesC
 */
class MotionEventDemoAActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_event_demo_aactivity)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> log("dispatchTouchEvent ACTION_DOWN")
            MotionEvent.ACTION_MOVE -> log("dispatchTouchEvent ACTION_MOVE")
            MotionEvent.ACTION_UP -> log("dispatchTouchEvent ACTION_UP")
        }
        val flag = super.dispatchTouchEvent(event)
        log("dispatchTouchEvent return: $flag")
        return flag
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> log("onTouchEvent ACTION_DOWN")
            MotionEvent.ACTION_MOVE -> log("onTouchEvent ACTION_MOVE")
            MotionEvent.ACTION_UP -> log("onTouchEvent ACTION_UP")
        }
        val flag = super.onTouchEvent(event)
        log("onTouchEvent return: $flag")
        return flag
    }

}
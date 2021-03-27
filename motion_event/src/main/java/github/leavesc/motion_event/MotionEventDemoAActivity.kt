package github.leavesc.motion_event

import android.view.MotionEvent
import github.leavesc.base.BaseActivity
import github.leavesc.motion_event.databinding.ActivityMotionEventDemoABinding

/**
 * @Author: leavesC
 * @Date: 2021/1/16 0:34
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class MotionEventDemoAActivity : BaseActivity() {

    override val bind by getBind<ActivityMotionEventDemoABinding>()

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
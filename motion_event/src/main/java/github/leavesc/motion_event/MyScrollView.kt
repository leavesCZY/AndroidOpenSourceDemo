package github.leavesc.motion_event

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import kotlin.math.abs

class ExternalScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {

    override fun onInterceptTouchEvent(motionEvent: MotionEvent): Boolean {
        val intercepted: Boolean
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                intercepted = false
                super.onInterceptTouchEvent(motionEvent)
            }
            else -> {
                intercepted = true
            }
        }
        return intercepted
    }

}


class InsideScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {

    private var lastX = 0f

    private var lastY = 0f

    override fun dispatchTouchEvent(motionEvent: MotionEvent): Boolean {
        val x = motionEvent.x
        val y = motionEvent.y
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = x - lastX
                val deltaY = y - lastY
                if (abs(deltaX) < abs(deltaY)) { //上下滑动的操作
                    if (deltaY > 0) { //向下滑动
                        if (scrollY == 0) { //滑动到顶部了
                            parent.requestDisallowInterceptTouchEvent(false)
                        }
                    } else { //向上滑动
                        if (height + scrollY >= computeVerticalScrollRange()) { //滑动到底部了
                            parent.requestDisallowInterceptTouchEvent(false)
                        }
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        lastX = x
        lastY = y
        return super.dispatchTouchEvent(motionEvent)
    }

}
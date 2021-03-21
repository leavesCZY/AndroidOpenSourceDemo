package github.leavesc.customview.view.floatball

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.widget.Toast
import github.leavesc.customview.utils.Utils

/**
 * @Author: leavesC
 * @Date: 2020/03/21 11:52
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
@SuppressLint("ClickableViewAccessibility")
object FloatBallViewManager {

    private val context: Context
        get() = Utils.context

    private val windowManager by lazy {
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    private val floatBallView by lazy {
        FloatBallView(context)
    }

    private val floatBallWindowParams: WindowManager.LayoutParams by lazy {
        WindowManager.LayoutParams().apply {
            width = FloatBallView.VIEW_WIDTH
            height = FloatBallView.VIEW_HEIGHT
            gravity = Gravity.START or Gravity.CENTER_VERTICAL
            flags =
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
            type = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
            } else {
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            }
        }
    }

    init {
        val touchListener = object : OnTouchListener {

            private var downX = 0f

            private var downY = 0f

            private var tempX = 0f

            private var tempY = 0f

            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        downX = event.rawX
                        downY = event.rawY
                        tempX = event.rawX
                        tempY = event.rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val x = event.rawX - downX
                        val y = event.rawY - downY
                        floatBallWindowParams.x += x.toInt()
                        floatBallWindowParams.y += y.toInt()
                        floatBallView.setDragState(true)
                        windowManager.updateViewLayout(floatBallView, floatBallWindowParams)
                        downX = event.rawX
                        downY = event.rawY
                    }
                    MotionEvent.ACTION_UP -> {
                        val endX = if (event.rawX < Utils.screenWidth / 2f) {
                            0f
                        } else {
                            (Utils.screenWidth - FloatBallView.VIEW_WIDTH).toFloat()
                        }
                        floatBallWindowParams.x = endX.toInt()
                        floatBallView.setDragState(false)
                        windowManager.updateViewLayout(floatBallView, floatBallWindowParams)
                    }
                }
                return false
            }
        }
        floatBallView.setOnTouchListener(touchListener)
        floatBallView.setOnClickListener {
            Toast.makeText(Utils.context, "业志陈", Toast.LENGTH_SHORT).show()
        }
    }

    fun showFloatBall() {
        windowManager.addView(floatBallView, floatBallWindowParams)
    }

}
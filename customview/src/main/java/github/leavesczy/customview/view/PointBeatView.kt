package github.leavesczy.customview.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import github.leavesczy.customview.base.BaseView

/**
 * @Author: leavesCZY
 * @Date: 2020/03/12 01:05
 * @Github：https://github.com/leavesCZY
 * @Desc:
 */
class PointBeatView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : BaseView(context, attrs, defStyleAttr, defStyleRes) {

    private class Point {
        var x = 0f
        var y = 0f
        var radius = 0f
    }

    //小球
    private val ballPoint = Point()

    //贝塞尔曲线控制点
    private val controlPoint = Point()

    private var lineY = 0f

    private var lineXLeft = 0f

    private var lineXRight = 0f

    //小球最高点Y坐标
    private var pointYMin = 0f

    private val paint = Paint().apply {
        isAntiAlias = true
        isDither = true
    }

    private val path = Path()

    private val downAnimator = ValueAnimator().apply {
        //加速下降
        interpolator = AccelerateInterpolator()
        addUpdateListener { animation: ValueAnimator ->
            ballPoint.y = animation.animatedValue as Float
            if (ballPoint.y + ballPoint.radius <= lineY) {
                controlPoint.y = lineY
            } else {
                controlPoint.y = lineY + 2 * (ballPoint.y + ballPoint.radius - lineY)
            }
            invalidate()
        }
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                startUpAnimator()
            }
        })
    }

    private val upAnimator = ValueAnimator().apply {
        //减速上升
        interpolator = DecelerateInterpolator()
        addUpdateListener { animation: ValueAnimator ->
            ballPoint.y = animation.animatedValue as Float
            if (ballPoint.y + ballPoint.radius >= lineY) { //还处于水平线以下
                controlPoint.y = lineY + 2 * (ballPoint.y + ballPoint.radius - lineY)
            } else {
                //小球总的要上升的距离
                val tempY = lineY - pointYMin
                //小球最低点距离水平线的距离，即小球已上升的距离
                val distance = lineY - ballPoint.y - ballPoint.radius
                //上升比例
                val percentage = distance / tempY
                when {
                    percentage <= 0.2 -> {  //线从水平线升高到最高点
                        controlPoint.y = lineY + 2 * (ballPoint.y + ballPoint.radius - lineY)
                    }
                    percentage <= 0.28 -> { //线从最高点降落到水平线
                        controlPoint.y = lineY - (distance - tempY * 0.2f)
                    }
                    percentage <= 0.34 -> { //线从水平线降落到最低点
                        controlPoint.y = lineY + (distance - tempY * 0.28f)
                    }
                    percentage <= 0.39 -> { //线从最低点升高到水平线
                        controlPoint.y = lineY - (distance - tempY * 0.34f)
                    }
                    else -> {
                        controlPoint.y = lineY
                    }
                }
            }
            invalidate()
        }
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                startDownAnimator()
            }
        })
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = getSize(widthMeasureSpec, screenWidth)
        val height = getSize(heightMeasureSpec, screenHeight)
        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(contentWidth: Int, contentHeight: Int, oldW: Int, oldH: Int) {
        super.onSizeChanged(contentWidth, contentHeight, oldW, oldH)
        lineY = contentHeight * 0.5f
        lineXLeft = contentWidth * 0.15f
        lineXRight = contentWidth * 0.85f

        //小球最低点Y坐标
        val pointYMax = contentHeight * 0.55f
        pointYMin = contentHeight * 0.22f
        ballPoint.x = contentWidth * 0.5f
        ballPoint.radius = 26f
        ballPoint.y = pointYMin
        controlPoint.x = ballPoint.x
        val speed: Long = 1500
        downAnimator.setFloatValues(pointYMin, pointYMax)
        upAnimator.setFloatValues(pointYMax, pointYMin)
        downAnimator.duration = speed
        upAnimator.duration = (0.8 * speed).toLong()
        startAnimator()
    }

    override fun onDraw(canvas: Canvas) {
        paint.color = Color.WHITE
        paint.strokeWidth = 8f
        path.reset()
        path.moveTo(lineXLeft, lineY)
        path.quadTo(controlPoint.x, controlPoint.y, lineXRight, lineY)
        paint.style = Paint.Style.STROKE
        canvas.drawPath(path, paint)
        paint.style = Paint.Style.FILL
        canvas.drawCircle(lineXLeft, lineY, 16f, paint)
        canvas.drawCircle(lineXRight, lineY, 16f, paint)
        paint.color = Color.parseColor("#f7584d")
        paint.strokeWidth = 0f
        canvas.drawCircle(ballPoint.x, ballPoint.y, ballPoint.radius, paint)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopAnimator()
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        when (visibility) {
            VISIBLE -> {
                startAnimator()
            }
            INVISIBLE, GONE -> {
                stopAnimator()
            }
        }
    }

    private fun startAnimator() {
        startDownAnimator()
    }

    private fun stopAnimator() {
        stopDownAnimator()
        stopUpAnimator()
    }

    private fun startDownAnimator() {
        if (downAnimator.values != null && downAnimator.values.isNotEmpty() && !downAnimator.isRunning) {
            downAnimator.start()
        }
    }

    private fun stopDownAnimator() {
        if (downAnimator.isRunning) {
            downAnimator.cancel()
        }
    }

    private fun startUpAnimator() {
        if (upAnimator.values != null && upAnimator.values.isNotEmpty() && !upAnimator.isRunning) {
            upAnimator.start()
        }
    }

    private fun stopUpAnimator() {
        if (upAnimator.isRunning) {
            upAnimator.cancel()
        }
    }

}

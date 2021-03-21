package github.leavesc.customview.view

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import github.leavesc.customview.base.BaseView

/**
 * @Author: leavesC
 * @Date: 2020/03/12 01:05
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
class CircleRefreshView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : BaseView(context, attrs, defStyleAttr, defStyleRes) {

    private data class Circle(var x: Float, var y: Float, var radius: Float, var color: Int)

    companion object {

        private const val LEFT = 0

        private const val RIGHT = 1

        private const val CENTER = 2

        //View的默认宽度，dp
        private const val DEFAULT_WIDTH = 80

        //View的默认高度，dp
        private const val DEFAULT_HEIGHT = 50

        //每个圆的默认最大半径
        private const val DEFAULT_MAX_RADIUS = 8

        //每个圆的默认最小半径
        private const val DEFAULT_MIN_RADIUS = 6

        //默认速度
        private const val DEFAULT_SPEED = 3000L

    }

    var speed = DEFAULT_SPEED
        set(value) {
            field = value
            animator.duration = value
        }

    private var contentWidth = 0

    private var contentHeight = 0

    //中心圆与相邻两个圆的圆心间隔
    private var gap = 0

    //圆最大半径
    private var maxRadius = dp2px(DEFAULT_MAX_RADIUS)

    //圆最小半径
    private var minRadius = dp2px(DEFAULT_MIN_RADIUS)

    //绿色
    private val colorCircleLeft = Color.parseColor("#008577")

    //橙色
    private val colorCircleCenter = Color.parseColor("#f96630")

    //红色
    private val colorCircleRight = Color.parseColor("#f54183")

    private val circleList = mutableListOf<Circle>()

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val animator = ValueAnimator.ofFloat(0f, 1f).apply {
        duration = speed
        repeatCount = -1
        repeatMode = ValueAnimator.RESTART
        interpolator = LinearInterpolator()
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                //当动画启动时，将三个圆的位置重置到准备开启动画的临界状态
                resetToStart()
            }

            override fun onAnimationEnd(animation: Animator) {}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        addUpdateListener { animation: ValueAnimator ->
            //循环刷新三个圆的位置
            for (i in circleList.indices) {
                updateCircle(i, animation.animatedFraction)
            }
            invalidate()
        }
    }

    //将三个圆的位置重置到准备开启动画的临界状态
    private fun resetToStart() {
        var circle = circleList[LEFT]
        circle.x = minRadius.toFloat()
        circle.radius = minRadius.toFloat()
        circle = circleList[RIGHT]
        circle.x = (contentWidth - minRadius).toFloat()
        circle.radius = minRadius.toFloat()
        circle = circleList[CENTER]
        circle.x = (contentWidth shr 1).toFloat()
        circle.radius = maxRadius.toFloat()
        invalidate()
    }

    private fun updateCircle(index: Int, fraction: Float) {
        //             x              x               x
        // ------------||-------------||--------------||------------
        //            1/4            2/4             3/4
        //      1/4            2/4            3/4            4/4

        //   左边-绿色
        // 半径从0到min  半径从min到max    半径从max到min   半径从min到0

        //   中间-橙色
        //                                半径从max到min   半径从min到0
        //半径从0到min   半径从min到max

        //   右边-红色
        //                                                 半径从min到0
        //半径从0到min  半径从min到max     半径从max到min
        var radius = 0f
        var x = 0f
        when (index) {
            LEFT -> {
                when {
                    fraction <= 1f / 4f -> {
                        radius = minRadius * (4f * fraction)
                        x = minRadius.toFloat()
                    }
                    fraction <= 0.5f -> {
                        val percent = (fraction - 1f / 4f) * 4f
                        radius = minRadius + percent * (maxRadius - minRadius)
                        x = minRadius + percent * (contentWidth / 2f - minRadius)
                    }
                    fraction <= 3f / 4f -> {
                        val percent = (fraction - 0.5f) * 4f
                        radius = maxRadius - percent * (maxRadius - minRadius)
                        x = contentWidth / 2f + percent * (contentWidth / 2f - minRadius)
                    }
                    else -> {
                        radius = minRadius - (fraction - 3f / 4f) * 4f * minRadius
                        x = (contentWidth - minRadius).toFloat()
                    }
                }
            }
            CENTER -> {
                when {
                    fraction <= 1f / 4f -> {
                        val percent = fraction * 4f
                        radius = maxRadius - (maxRadius - minRadius) * percent
                        x = contentWidth / 2f + (contentWidth / 2f - minRadius) * percent
                    }
                    fraction <= 0.5f -> {
                        radius = minRadius - (fraction - 1f / 4f) * 4f * minRadius
                        x = (contentWidth - minRadius).toFloat()
                    }
                    fraction <= 3f / 4f -> {
                        radius = minRadius * (4f * (fraction - 0.5f))
                        x = minRadius.toFloat()
                    }
                    else -> {
                        val percent = (fraction - 3f / 4f) * 4f
                        radius = minRadius + (maxRadius - minRadius) * percent
                        x = minRadius + (contentWidth / 2f - minRadius) * percent
                    }
                }
            }
            RIGHT -> {
                when {
                    fraction <= 1f / 4f -> {
                        radius = minRadius - 4f * fraction * minRadius
                        x = (contentWidth - minRadius).toFloat()
                    }
                    fraction <= 0.5f -> {
                        radius = minRadius * (4f * (fraction - 1f / 4f))
                        x = minRadius.toFloat()
                    }
                    fraction <= 3f / 4f -> {
                        val percent = (fraction - 0.5f) * 4f
                        radius = minRadius + (maxRadius - minRadius) * percent
                        x = minRadius + (contentWidth / 2f - minRadius) * percent
                    }
                    else -> {
                        val percent = (fraction - 3f / 4f) * 4f
                        radius = maxRadius - (maxRadius - minRadius) * percent
                        x = contentWidth / 2f + (contentWidth / 2f - minRadius) * percent
                    }
                }
            }
        }
        val circle = circleList[index]
        circle.radius = radius
        circle.x = x
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        contentWidth = measuredWidth - paddingLeft - paddingRight
        contentHeight = measuredHeight - paddingTop - paddingBottom
        resetCircles()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = getSize(widthMeasureSpec, dp2px(DEFAULT_WIDTH))
        val height = getSize(heightMeasureSpec, dp2px(DEFAULT_HEIGHT))
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        for (circle in circleList) {
            paint.color = circle.color
            canvas.drawCircle(
                circle.x + paddingLeft, circle.y + paddingTop, circle.radius,
                paint
            )
        }
    }

    fun drag(fraction: Float) {
        if (animator.isRunning) {
            return
        }
        if (fraction > 1) {
            return
        }
        circleList[LEFT].x = minRadius + gap * (1f - fraction)
        circleList[RIGHT].x = (contentWidth / 2f) + gap * fraction
        invalidate()
    }

    private fun resetCircles() {
        val x = contentWidth / 2f
        val y = contentHeight / 2f
        if (circleList.isEmpty()) {
            gap = (x - minRadius).toInt()
            val circleLeft = Circle(
                x, y,
                minRadius.toFloat(), colorCircleLeft
            )
            val circleCenter = Circle(
                x, y,
                maxRadius.toFloat(), colorCircleCenter
            )
            val circleRight = Circle(
                x, y,
                minRadius.toFloat(), colorCircleRight
            )
            circleList.add(LEFT, circleLeft)
            circleList.add(RIGHT, circleRight)
            circleList.add(CENTER, circleCenter)
        } else {
            for (i in circleList.indices) {
                val circle = circleList[i]
                circle.x = x
                circle.y = y
                if (i == CENTER) {
                    circle.radius = maxRadius.toFloat()
                } else {
                    circle.radius = minRadius.toFloat()
                }
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopAnimator()
        animator.removeAllUpdateListeners()
    }

    fun startAnimator() {
        if (!animator.isRunning) {
            animator.start()
        }
    }

    fun stopAnimator() {
        if (animator.isRunning) {
            animator.cancel()
            resetCircles()
            invalidate()
        }
    }

}
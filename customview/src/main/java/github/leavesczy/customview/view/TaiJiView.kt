package github.leavesczy.customview.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import github.leavesczy.customview.base.BaseView

/**
 * @Author: leavesCZY
 * @Date: 2020/03/12 01:05
 * @Github：https://github.com/leavesCZY
 * @Desc:
 */
class TaiJiView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, defStyleRes: Int = 0
) : BaseView(context, attrs, defStyleAttr, defStyleRes) {

    private var radius = 0f

    private var centerX = 0f

    private var centerY = 0f

    private val paint = Paint().apply {
        isAntiAlias = true
        isDither = true
    }

    private val rectF = RectF()

    private var degreesAnimatedValue = 0f

    private val valueAnimator = ValueAnimator().apply {
        setFloatValues(0f, 360f)
        duration = 3000
        repeatCount = ValueAnimator.INFINITE
        interpolator = LinearInterpolator()
        addUpdateListener { animation: ValueAnimator ->
            this@TaiJiView.degreesAnimatedValue = animation.animatedValue as Float
            invalidate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = getSize(widthMeasureSpec, screenWidth).coerceAtMost(
            getSize(
                heightMeasureSpec,
                screenHeight
            )
        )
        setMeasuredDimension(width, width)
    }

    override fun onSizeChanged(w: Int, h: Int, oldW: Int, oldH: Int) {
        val width = (w - paddingLeft - paddingRight).coerceAtMost(h - paddingTop - paddingBottom)
        radius = width / 2f
        centerX = paddingLeft + radius
        centerY = paddingTop + radius
    }

    override fun onDraw(canvas: Canvas) {
        val realRadius = radius
        val halfRadius = realRadius / 2f
        val pointRadius = halfRadius / 8f

        canvas.translate(centerX, centerY)
        canvas.rotate(degreesAnimatedValue)

        //绘制边框
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1f
        canvas.drawCircle(0f, 0f, realRadius, paint)

        //绘制左右半圆
        rectF.set(-realRadius, -realRadius, realRadius, realRadius)
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 0f
        paint.color = Color.BLACK
        canvas.drawArc(rectF, 90f, 180f, true, paint)
        paint.color = Color.WHITE
        canvas.drawArc(rectF, -90f, 180f, true, paint)

        //绘制上边的白色圆
        canvas.save()
        canvas.translate(0f, -halfRadius)
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 1f
        canvas.drawCircle(0f, 0f, halfRadius, paint)
        paint.color = Color.BLACK
        canvas.drawCircle(0f, 0f, pointRadius, paint)
        canvas.restore()

        //绘制上边的黑色圆
        canvas.save()
        canvas.translate(0f, halfRadius)
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        canvas.drawCircle(0f, 0f, halfRadius, paint)
        paint.color = Color.WHITE
        canvas.drawCircle(0f, 0f, pointRadius, paint)
        canvas.restore()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAnimator()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopAnimator()
        valueAnimator.removeAllUpdateListeners()
    }

    private fun startAnimator() {
        if (!valueAnimator.isRunning) {
            valueAnimator.start()
        }
    }

    private fun stopAnimator() {
        if (valueAnimator.isRunning) {
            valueAnimator.cancel()
        }
    }

}
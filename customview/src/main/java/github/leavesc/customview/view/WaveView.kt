package github.leavesc.customview.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import github.leavesc.customview.base.BaseView

/**
 * @Author: leavesC
 * @Date: 2020/03/12 01:05
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
class WaveView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, defStyleRes: Int = 0
) : BaseView(context, attrs, defStyleAttr, defStyleRes) {

    companion object {

        //每个波浪的宽度占据 View 宽度的默认比例
        private const val DEFAULT_WAVE_SCALE_WIDTH = 1f

        //每个波浪的高度占据 View 高度的默认比例
        private const val DEFAULT_WAVE_SCALE_HEIGHT = 0.035f

        //波浪的默认速度
        private const val DEFAULT_SPEED = 800L

        //波浪的默认背景色
        private val DEFAULT_BG_COLOR = Color.parseColor("#FF018786")

    }

    private var contentWidth = 0

    private var contentHeight = 0

    //每个波浪的起伏高度
    private var waveHeight = 0f

    //每个波浪的宽度
    private var waveWidth = 0f

    //波浪的速度
    var speed = DEFAULT_SPEED
        set(value) {
            field = value
            resetWaveParams()
        }

    var bgColor = DEFAULT_BG_COLOR
        set(value) {
            field = value
            wavePaint.color = value
        }

    var waveWidthScale = 0f
        set(value) {
            if (value <= 0 || value > 1) {
                return
            }
            field = value
            resetWaveParams()
        }

    var waveHeightScale = 0f
        set(value) {
            if (value <= 0 || value > 1) {
                return
            }
            field = value
            resetWaveParams()
        }

    private var animatedValue = 0f

    private val wavePath = Path()

    private val wavePaint = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = bgColor
        style = Paint.Style.FILL
    }

    private val valueAnimator = ValueAnimator().apply {
        duration = speed
        repeatCount = ValueAnimator.INFINITE
        interpolator = LinearInterpolator()
        addUpdateListener { animation: ValueAnimator ->
            this@WaveView.animatedValue = animation.animatedValue as Float
            invalidate()
        }
    }

    init {
        waveWidthScale = DEFAULT_WAVE_SCALE_WIDTH
        waveHeightScale = DEFAULT_WAVE_SCALE_HEIGHT
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            getSize(widthMeasureSpec, screenWidth),
            getSize(heightMeasureSpec, screenHeight)
        )
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        contentWidth = width
        contentHeight = height
        resetWaveParams()
    }

    override fun onDraw(canvas: Canvas) {
        wavePath.reset()
        wavePath.moveTo(-waveWidth + animatedValue, contentHeight / 2f)
        var i = -waveWidth
        while (i < contentWidth + waveWidth) {
            wavePath.rQuadTo(waveWidth / 4f, -waveHeight, waveWidth / 2f, 0f)
            wavePath.rQuadTo(waveWidth / 4f, waveHeight, waveWidth / 2f, 0f)
            i += waveWidth
        }
        wavePath.lineTo(contentWidth.toFloat(), contentHeight.toFloat())
        wavePath.lineTo(0f, contentHeight.toFloat())
        wavePath.close()
        canvas.drawPath(wavePath, wavePaint)
    }

    private fun resetWaveParams() {
        waveWidth = contentWidth * waveWidthScale
        waveHeight = contentHeight * waveHeightScale
        valueAnimator.setFloatValues(0f, waveWidth)
        valueAnimator.duration = speed
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
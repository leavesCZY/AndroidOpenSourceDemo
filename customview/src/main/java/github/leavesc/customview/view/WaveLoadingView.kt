package github.leavesc.customview.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.ColorInt
import github.leavesc.customview.R
import github.leavesc.customview.base.BaseView

/**
 * @Author: leavesC
 * @Date: 2020/03/12 01:05
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
class WaveLoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, defStyleRes: Int = 0
) : BaseView(context, attrs, defStyleAttr, defStyleRes) {

    companion object {

        //每个波浪的宽度占据View宽度的默认比例
        private const val DEFAULT_WAVE_SCALE_WIDTH = 0.8f

        //每个波浪的高度占据View高度的默认比例
        private const val DEFAULT_WAVE_SCALE_HEIGHT = 0.13f

        //波浪的默认颜色
        private val DEFAULT_WAVE_COLOR = Color.parseColor("#f54183")

        //文本下方的默认颜色
        private const val DEFAULT_DOWN_TEXT_COLOR = Color.WHITE

        //默认文本大小，sp
        private const val DEFAULT_TEXT_SIZE = 150

        //波浪的默认速度
        private const val DEFAULT_SPEED = 900L

        //View的默认大小，dp
        private const val DEFAULT_SIZE = 220

    }

    private var waveScaleWidth = 0f

    private var waveScaleHeight = 0f

    @ColorInt
    private var waveColor = 0

    @ColorInt
    private var downTextColor = 0

    //每个波浪的起伏高度
    private var waveHeight = 0f

    //每个波浪的宽度
    private var waveWidth = 0f

    //波浪的速度
    private var speed = 0L

    private var textSize = 0f

    private var text = 0.toChar()

    private var size = 0f

    private var animatedValue = 0f

    private val circlePath = Path()

    private val wavePath = Path()

    private var radius = 0f

    private var centerX = 0f

    private var centerY = 0f

    private val valueAnimator = ValueAnimator().apply {
        duration = speed
        repeatCount = ValueAnimator.INFINITE
        interpolator = LinearInterpolator()
        addUpdateListener { animation: ValueAnimator ->
            this@WaveLoadingView.animatedValue = animation.animatedValue as Float
            invalidate()
        }
    }

    init {
        initAttributeSet(context, attrs)
        resetWaveParams()
    }

    private val wavePaint = Paint().apply {
        isAntiAlias = true
        isDither = true
        color = waveColor
        style = Paint.Style.FILL
        strokeWidth = 0f
    }

    private val textPaint = Paint().apply {
        isAntiAlias = true
        isDither = true
        style = Paint.Style.FILL
        typeface = Typeface.DEFAULT_BOLD
        textSize = this@WaveLoadingView.textSize
    }

    private fun initAttributeSet(context: Context, attrs: AttributeSet?) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.WaveLoadingView)
        waveScaleWidth =
            array.getFloat(R.styleable.WaveLoadingView_scaleWidth, DEFAULT_WAVE_SCALE_WIDTH)
        waveScaleHeight =
            array.getFloat(R.styleable.WaveLoadingView_scaleHeight, DEFAULT_WAVE_SCALE_HEIGHT)
        waveColor = array.getColor(R.styleable.WaveLoadingView_waveColor, DEFAULT_WAVE_COLOR)
        downTextColor =
            array.getColor(R.styleable.WaveLoadingView_downTextColor, DEFAULT_DOWN_TEXT_COLOR)
        textSize = array.getDimension(
            R.styleable.WaveLoadingView_textSize,
            sp2px(DEFAULT_TEXT_SIZE).toFloat()
        )
        speed = array.getInt(R.styleable.WaveLoadingView_speed, DEFAULT_SPEED.toInt()).toLong()
        val centerText = array.getString(R.styleable.WaveLoadingView_centerText)
        if (!centerText.isNullOrBlank()) {
            text = centerText[0]
        }
        array.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val defaultSize = dp2px(DEFAULT_SIZE)
        var width = getSize(widthMeasureSpec, defaultSize)
        var height = getSize(heightMeasureSpec, defaultSize)
        height = width.coerceAtMost(height)
        width = height
        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldW: Int, oldH: Int) {
        super.onSizeChanged(w, h, oldW, oldH)
        size = w.coerceAtMost(h).toFloat()
        radius = size / 2f
        centerX = radius
        centerY = radius
        resetWaveParams()
    }

    override fun onDraw(canvas: Canvas) {
        textPaint.color = waveColor
        drawText(canvas, textPaint, text.toString())
        wavePath.reset()
        wavePath.moveTo(-waveWidth + animatedValue, size / 2.2f)
        var i = -waveWidth
        while (i < size + waveWidth) {
            wavePath.rQuadTo(waveWidth / 4f, -waveHeight, waveWidth / 2f, 0f)
            wavePath.rQuadTo(waveWidth / 4f, waveHeight, waveWidth / 2f, 0f)
            i += waveWidth
        }
        wavePath.lineTo(size, size)
        wavePath.lineTo(0f, size)
        wavePath.close()
        circlePath.reset()
        circlePath.addCircle(centerX, centerY, radius - 1, Path.Direction.CCW)
        circlePath.op(wavePath, Path.Op.INTERSECT)
        canvas.drawPath(circlePath, wavePaint)
        canvas.clipPath(circlePath)
        textPaint.color = downTextColor
        drawText(canvas, textPaint, text.toString())
    }

    private fun drawText(canvas: Canvas, textPaint: Paint, text: String) {
        val rect = RectF(0f, 0f, size, size)
        textPaint.textAlign = Paint.Align.CENTER
        val fontMetrics = textPaint.fontMetrics
        val top = fontMetrics.top
        val bottom = fontMetrics.bottom
        val centerY = rect.centerY() - top / 2f - bottom / 2f
        canvas.drawText(text, rect.centerX(), centerY, textPaint)
    }

    private fun resetWaveParams() {
        waveWidth = size * waveScaleWidth
        waveHeight = size * waveScaleHeight
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
package github.leavesc.customview.base

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @Author: leavesC
 * @Date: 2020/03/12 01:05
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
open class BaseView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    @JvmField
    protected val tag = this.javaClass.simpleName

    protected val screenWidth: Int
        get() = resources.displayMetrics.widthPixels

    protected val screenHeight: Int
        get() = resources.displayMetrics.heightPixels

    protected fun getSize(measureSpec: Int, defaultSize: Int): Int {
        return when (MeasureSpec.getMode(measureSpec)) {
            MeasureSpec.AT_MOST -> {
                MeasureSpec.getSize(measureSpec).coerceAtMost(defaultSize)
            }
            MeasureSpec.EXACTLY -> {
                MeasureSpec.getSize(measureSpec)
            }
            MeasureSpec.UNSPECIFIED -> {
                defaultSize
            }
            else -> {
                defaultSize
            }
        }
    }

    protected fun dp2px(dpValue: Number): Int {
        return (dpValue.toDouble() * context.resources.displayMetrics.density + 0.5f).toInt()
    }

    protected fun px2dp(pxValue: Number): Int {
        return (pxValue.toDouble() / context.resources.displayMetrics.density + 0.5f).toInt()
    }

    protected fun sp2px(spValue: Number): Int {
        return (spValue.toDouble() * context.resources.displayMetrics.scaledDensity + 0.5f).toInt()
    }

    protected fun px2sp(pxValue: Number): Int {
        return (pxValue.toDouble() / context.resources.displayMetrics.scaledDensity + 0.5f).toInt()
    }

    fun log(log: Any?) {
        Log.e(tag, log?.toString() ?: "null")
    }

}
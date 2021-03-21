package github.leavesc.customview.view.floatball

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import github.leavesc.customview.R
import github.leavesc.customview.base.BaseView


/**
 * @Author: leavesC
 * @Date: 2020/03/21 11:52
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
class FloatBallView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : BaseView(context, attrs, defStyleAttr, defStyleRes) {

    companion object {

        const val VIEW_WIDTH = 150

        const val VIEW_HEIGHT = 150

    }

    private var isDrag = false

    private val bitmap by lazy {
        val src = BitmapFactory.decodeResource(resources, R.drawable.icon_no_drag)
        Bitmap.createScaledBitmap(src, VIEW_WIDTH, VIEW_HEIGHT, true)
    }

    private val bitmapDrag by lazy {
        val src = BitmapFactory.decodeResource(resources, R.drawable.icon_drag)
        Bitmap.createScaledBitmap(src, VIEW_WIDTH, VIEW_HEIGHT, true)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(VIEW_WIDTH, VIEW_HEIGHT)
    }

    override fun onDraw(canvas: Canvas) {
        if (isDrag) {
            canvas.drawBitmap(bitmapDrag, 0f, 0f, null)
        } else {
            canvas.drawBitmap(bitmap, 0f, 0f, null)
        }
    }

    fun setDragState(isDrag: Boolean) {
        if (this.isDrag != isDrag) {
            this.isDrag = isDrag
            invalidate()
        }
    }

}
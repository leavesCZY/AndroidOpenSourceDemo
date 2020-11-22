package github.leavesc.coil.transformation

import android.graphics.*
import androidx.annotation.ColorInt
import coil.bitmap.BitmapPool
import coil.size.Size
import coil.transform.Transformation

/**
 * @Author: leavesC
 * @Date: 2020/11/22 11:17
 * @GitHub：https://github.com/leavesC
 * @Desc: 添加蒙层
 */
class ColorFilterTransformation(
    @ColorInt private val color: Int
) : Transformation {

    override fun key(): String = "${ColorFilterTransformation::class.java.name}-$color"

    override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
        val width = input.width
        val height = input.height
        val config = input.config
        val output = pool.get(width, height, config)

        val canvas = Canvas(output)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        canvas.drawBitmap(input, 0f, 0f, paint)

        return output
    }
}
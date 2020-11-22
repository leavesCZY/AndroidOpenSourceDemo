package github.leavesc.coil.transformation

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import coil.bitmap.BitmapPool
import coil.size.Size
import coil.transform.Transformation

/**
 * @Author: leavesC
 * @Date: 2020/11/22 11:32
 * @GitHub：https://github.com/leavesC
 * @Desc: 为图片添加水印
 */
class WatermarkTransformation(
    private val watermark: String,
    @ColorInt private val textColor: Int,
    private val textSize: Float
) : Transformation {

    override fun key(): String {
        return "${WatermarkTransformation::class.java.name}-${watermark}-${textColor}-${textSize}"
    }

    override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
        val width = input.width
        val height = input.height
        val config = input.config

        val output = pool.get(width, height, config)

        val canvas = Canvas(output)
        val paint = Paint()
        paint.isAntiAlias = true
        canvas.drawBitmap(input, 0f, 0f, paint)

        canvas.rotate(40f, width / 2f, height / 2f)

        paint.textSize = textSize
        paint.color = textColor

        val textWidth = paint.measureText(watermark)

        canvas.drawText(watermark, (width - textWidth) / 2f, height / 2f, paint)

        return output
    }

}
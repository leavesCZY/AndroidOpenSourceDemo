package github.leavesc.customview.utils

import android.content.Context
import android.graphics.Color
import github.leavesc.base.ContextHolder
import java.util.*
import kotlin.random.Random

/**
 * @Author: leavesC
 * @Date: 2020/03/12 01:05
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
object Utils {

    val context: Context
        get() = ContextHolder.context

    val screenWidth: Int
        get() = context.resources.displayMetrics.widthPixels

    fun getRandomColor(): String {
        var r: String = Integer.toHexString(Random.nextInt(256)).uppercase(Locale.ROOT)
        var g: String = Integer.toHexString(Random.nextInt(256)).uppercase(Locale.ROOT)
        var b: String = Integer.toHexString(Random.nextInt(256)).uppercase(Locale.ROOT)
        r = if (r.length == 1) "0$r" else r
        g = if (g.length == 1) "0$g" else g
        b = if (b.length == 1) "0$b" else b
        return "#$r$g$b"
    }

    fun getRandomColorInt(): Int {
        var r: String = Integer.toHexString(Random.nextInt(256)).uppercase(Locale.ROOT)
        var g: String = Integer.toHexString(Random.nextInt(256)).uppercase(Locale.ROOT)
        var b: String = Integer.toHexString(Random.nextInt(256)).uppercase(Locale.ROOT)
        r = if (r.length == 1) "0$r" else r
        g = if (g.length == 1) "0$g" else g
        b = if (b.length == 1) "0$b" else b
        return Color.parseColor("#$r$g$b")
    }

}
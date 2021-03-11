package github.leavesc.customview.utils

import android.graphics.Color
import java.util.*
import kotlin.random.Random

/**
 * 作者：leavesC
 * 时间：2020/03/12 01:05
 * 描述：
 * GitHub：https://github.com/leavesC
 */
object Utils {

    fun getRandomColor(): String {
        var r: String = Integer.toHexString(Random.nextInt(256)).toUpperCase(Locale.ROOT)
        var g: String = Integer.toHexString(Random.nextInt(256)).toUpperCase(Locale.ROOT)
        var b: String = Integer.toHexString(Random.nextInt(256)).toUpperCase(Locale.ROOT)
        r = if (r.length == 1) "0$r" else r
        g = if (g.length == 1) "0$g" else g
        b = if (b.length == 1) "0$b" else b
        return "#$r$g$b"
    }

    fun getRandomColorInt(): Int {
        var r: String = Integer.toHexString(Random.nextInt(256)).toUpperCase(Locale.ROOT)
        var g: String = Integer.toHexString(Random.nextInt(256)).toUpperCase(Locale.ROOT)
        var b: String = Integer.toHexString(Random.nextInt(256)).toUpperCase(Locale.ROOT)
        r = if (r.length == 1) "0$r" else r
        g = if (g.length == 1) "0$g" else g
        b = if (b.length == 1) "0$b" else b
        return Color.parseColor("#$r$g$b")
    }

}
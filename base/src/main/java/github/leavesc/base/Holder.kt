package github.leavesc.base

import android.content.Context
import android.widget.Toast

/**
 * 作者：leavesC
 * 时间：2020/10/2 13:21
 * 描述：
 * GitHub：https://github.com/leavesC
 */
object ContextHolder {

    lateinit var context: Context

}

fun showToast(msg: String) {
    Toast.makeText(ContextHolder.context, msg, Toast.LENGTH_SHORT).show()
}
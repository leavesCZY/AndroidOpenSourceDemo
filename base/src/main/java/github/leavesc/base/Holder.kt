package github.leavesc.base

import android.content.Context
import android.widget.Toast

/**
 * @Author: leavesC
 * @Date: 2020/10/2 13:21
 * @Desc:
 * @Github：https://github.com/leavesC
 */
object ContextHolder {

    lateinit var context: Context

}

fun showToast(msg: String) {
    Toast.makeText(ContextHolder.context, msg, Toast.LENGTH_SHORT).show()
}
package github.leavesc.demo.utils

import android.widget.Toast
import github.leavesc.demo.MyApp

/**
 * 作者：leavesC
 * 时间：2020/10/2 13:21
 * 描述：
 * GitHub：https://github.com/leavesC
 */
fun showToast(msg: String) {
    Toast.makeText(MyApp.context, msg, Toast.LENGTH_SHORT).show()
}
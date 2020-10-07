package github.leavesc.demo.base

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

/**
 * 作者：leavesC
 * 时间：2020/10/2 13:14
 * 描述：
 * GitHub：https://github.com/leavesC
 */
open class BaseActivity : AppCompatActivity() {

    fun <T : Activity> startActivity(clazz: Class<T>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

}
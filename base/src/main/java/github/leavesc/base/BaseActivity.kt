package github.leavesc.base

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author: leavesC
 * @Date: 2020/12/26 22:02
 * @Desc:
 * @Github：https://github.com/leavesC
 */
open class BaseActivity : AppCompatActivity() {

    protected fun <T : Activity> startActivity(clazz: Class<T>) {
        startActivity(Intent(this, clazz))
    }

    protected fun log(log: Any?) {
        Log.e(javaClass.simpleName, log.toString())
    }

    protected fun showToast(msg: Any) {
        Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show()
    }

}
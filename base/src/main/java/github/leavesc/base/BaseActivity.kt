package github.leavesc.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * @Author: leavesC
 * @Date: 2020/12/26 22:02
 * @Desc:
 * @Github：https://github.com/leavesC
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract val bind: ViewBinding?

    protected inline fun <reified T> getBind(): Lazy<T> where T : ViewBinding {
        return lazy {
            val clazz = T::class.java
            val method = clazz.getMethod("inflate", LayoutInflater::class.java)
            method.invoke(null, layoutInflater) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind?.root?.apply {
            setContentView(this)
        }
    }

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
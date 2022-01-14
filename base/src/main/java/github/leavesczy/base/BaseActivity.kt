package github.leavesczy.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * @Author: leavesCZY
 * @Date: 2020/12/26 22:02
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
abstract class BaseActivity : AppCompatActivity() {

    protected open val logTag: String
        get() = javaClass.simpleName

    abstract val bind: ViewBinding?

    protected inline fun <reified T> getBind(): Lazy<T> where T : ViewBinding {
        return lazy {
            val clazz = T::class.java
            val method = clazz.getMethod("inflate", LayoutInflater::class.java)
            method.invoke(null, layoutInflater) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        log("onCreate-start")
        super.onCreate(savedInstanceState)
        bind?.root?.apply {
            setContentView(this)
        }
        log("onCreate-end")
    }

    override fun onStart() {
        log("onStart-start")
        super.onStart()
        log("onStart-end")
    }

    override fun onResume() {
        log("onResume-start")
        super.onResume()
        log("onResume-end")
    }

    override fun onPause() {
        log("onPause-start")
        super.onPause()
        log("onPause-end")
    }

    override fun onStop() {
        log("onStop-start")
        super.onStop()
        log("onStop-end")
    }

    override fun onDestroy() {
        log("onDestroy-start")
        super.onDestroy()
        log("onDestroy-end")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        log("onSaveInstanceState-start")
        super.onSaveInstanceState(outState)
        log("onSaveInstanceState-end")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        log("onRestoreInstanceState-start")
        super.onRestoreInstanceState(savedInstanceState)
        log("onRestoreInstanceState-end")
    }

    protected fun <T : Activity> startActivity(clazz: Class<T>) {
        startActivity(Intent(this, clazz))
    }

    protected inline fun <reified T : Activity> startActivity() {
        startActivity(Intent(this, T::class.java))
    }

    protected inline fun <reified T : Activity> startActivity(flag: Int) {
        startActivity(Intent(this, T::class.java).setFlags(flag))
    }

    protected open fun log(log: Any?) {
        Log.e(logTag, log.toString())
    }

    protected fun showToast(msg: Any) {
        Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show()
    }

}
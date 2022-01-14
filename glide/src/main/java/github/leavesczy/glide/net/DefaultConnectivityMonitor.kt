package github.leavesczy.glide.net

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager

/**
 * @Author: leavesCZY
 * @Date: 2020/11/7 14:40
 * @Desc:
 */
internal interface ConnectivityListener {
    fun onConnectivityChanged(isConnected: Boolean)
}

internal class DefaultConnectivityMonitor(
    context: Context,
    val listener: ConnectivityListener
) {

    private val appContext = context.applicationContext

    private var isConnected = false

    private var isRegistered = false

    private val connectivityReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val wasConnected = isConnected
            isConnected = isConnected(context)
            if (wasConnected != isConnected) {
                listener.onConnectivityChanged(isConnected)
            }
        }
    }

    private fun register() {
        if (isRegistered) {
            return
        }
        // Initialize isConnected.
        isConnected = isConnected(appContext)
        try {
            appContext.registerReceiver(
                connectivityReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
            isRegistered = true
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun unregister() {
        if (!isRegistered) {
            return
        }
        appContext.unregisterReceiver(connectivityReceiver)
        isRegistered = false
    }

    @SuppressLint("MissingPermission")
    private fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
                ?: return true
        val networkInfo = try {
            connectivityManager.activeNetworkInfo
        } catch (e: RuntimeException) {
            return true
        }
        return networkInfo != null && networkInfo.isConnected
    }

    fun onStart() {
        register()
    }

    fun onStop() {
        unregister()
    }

}

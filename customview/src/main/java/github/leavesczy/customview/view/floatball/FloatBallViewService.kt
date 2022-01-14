package github.leavesczy.customview.view.floatball

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * @Author: leavesCZY
 * @Date: 2020/03/21 11:52
 * @Github：https://github.com/leavesCZY
 * @Desc:
 */
class FloatBallViewService : Service() {

    override fun onBind(intent: Intent): IBinder {
        throw RuntimeException("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        FloatBallViewManager.showFloatBall()
    }

}
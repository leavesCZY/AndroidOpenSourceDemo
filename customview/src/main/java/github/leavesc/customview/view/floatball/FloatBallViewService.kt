package github.leavesc.customview.view.floatball

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * @Author: leavesC
 * @Date: 2020/03/21 11:52
 * @GitHub：https://github.com/leavesC
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
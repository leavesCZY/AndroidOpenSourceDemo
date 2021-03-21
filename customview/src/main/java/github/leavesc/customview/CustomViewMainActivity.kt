package github.leavesc.customview

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import github.leavesc.base.BaseActivity
import github.leavesc.customview.view.floatball.FloatBallViewService

/**
 * @Author: leavesC
 * @Date: 2020/03/12 01:05
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
class CustomViewMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view_main)
    }

    fun startWaveViewActivity(view: View) {
        startActivity(WaveViewActivity::class.java)
    }

    fun startWaveLoadingViewActivity(view: View) {
        ViewActivity.navTo(this, R.layout.activity_wave_loading_view)
    }

    fun startCircleRefreshViewActivity(view: View) {
        startActivity(CircleRefreshViewActivity::class.java)
    }

    fun startPointBeatViewActivity(view: View) {
        ViewActivity.navTo(this, R.layout.activity_point_beat_view)
    }

    fun startTaiJiViewActivity(view: View) {
        ViewActivity.navTo(this, R.layout.activity_tai_ji_view)
    }

    fun startFloatBallView(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            showToast("请先授予悬浮窗权限")
            val intent =
                Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
            startActivityForResult(intent, 0)
            return
        }
        startService(Intent(this, FloatBallViewService::class.java))
    }

}
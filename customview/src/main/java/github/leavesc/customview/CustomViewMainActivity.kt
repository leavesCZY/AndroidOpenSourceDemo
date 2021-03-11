package github.leavesc.customview

import android.os.Bundle
import android.view.View
import github.leavesc.base.BaseActivity

/**
 * 作者：leavesC
 * 时间：2020/03/12 01:05
 * 描述：
 * GitHub：https://github.com/leavesC
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

}
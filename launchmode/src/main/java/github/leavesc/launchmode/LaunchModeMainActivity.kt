package github.leavesc.launchmode

import android.os.Bundle
import android.util.Log
import github.leavesc.base.BaseActivity
import github.leavesc.launchmode.databinding.ActivityLaunchModeMainBinding

/**
 * @Author: leavesC
 * @Date: 2021/4/16 23:25
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class LaunchModeMainActivity : BaseActivity() {

    override val bind by getBind<ActivityLaunchModeMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.btnStartLaunchModeActivity.setOnClickListener {
            startActivity<StandardActivity>()
        }
        bind.btnStartIntentFlagActivity.setOnClickListener {
            startActivity<StandardActivity2>()
        }
    }

    private fun log(log: String) {
        Log.e("Activity", log + " " + "hashCode: " + hashCode() + " " + "taskId: " + taskId)
    }

}
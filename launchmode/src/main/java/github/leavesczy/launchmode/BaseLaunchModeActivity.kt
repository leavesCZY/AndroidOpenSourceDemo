package github.leavesczy.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import github.leavesczy.base.BaseActivity
import github.leavesczy.launchmode.databinding.ActivityBaseLaunchModeBinding

/**
 * @Author: leavesCZY
 * @Date: 2021/4/16 16:38
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
abstract class BaseLaunchModeActivity : BaseActivity() {

    override val bind by getBind<ActivityBaseLaunchModeBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.tvTips.text =
            getTip() + "\n" + "hashCode: " + hashCode() + "\n" + "taskId: " + taskId
        bind.btnStartStandardActivity.setOnClickListener {
            startActivity(StandardActivity::class.java)
        }
        bind.btnStartSingleTopActivity.setOnClickListener {
            startActivity(SingleTopActivity::class.java)
        }
        bind.btnStartSingleTaskActivity.setOnClickListener {
            startActivity(SingleTaskActivity::class.java)
        }
        bind.btnStartSingleInstanceActivity.setOnClickListener {
            startActivity(SingleInstanceActivity::class.java)
        }
        log("onCreate")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        log("onNewIntent")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    abstract fun getTip(): String

    private fun log(log: String) {
        Log.e(getTip(), log + " " + "hashCode: " + hashCode() + " " + "taskId: " + taskId)
    }

}

class StandardActivity : BaseLaunchModeActivity() {

    override fun getTip(): String {
        return "StandardActivity"
    }

}

class SingleTopActivity : BaseLaunchModeActivity() {

    override fun getTip(): String {
        return "SingleTopActivity"
    }

}

class SingleTaskActivity : BaseLaunchModeActivity() {

    override fun getTip(): String {
        return "SingleTaskActivity"
    }

}

class SingleInstanceActivity : BaseLaunchModeActivity() {

    override fun getTip(): String {
        return "SingleInstanceActivity"
    }

}
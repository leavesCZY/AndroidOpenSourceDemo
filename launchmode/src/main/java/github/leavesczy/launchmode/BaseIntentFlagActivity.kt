package github.leavesczy.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import github.leavesczy.base.BaseActivity
import github.leavesczy.launchmode.databinding.ActivityBaseIntentFlagBinding

/**
 * @Author: leavesCZY
 * @Date: 2021/4/16 23:25
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
abstract class BaseIntentFlagActivity : BaseActivity() {

    override val bind by getBind<ActivityBaseIntentFlagBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.tvTips.text =
            getTip() + "\n" + "hashCode: " + hashCode() + "\n" + "taskId: " + taskId
        bind.btnStartStandardActivity.setOnClickListener {
            startActivity<StandardActivity2>(getFlag())
        }
        bind.btnStartSingleTopActivity.setOnClickListener {
            startActivity<SingleTopActivity2>(getFlag())
        }
        bind.btnStartSingleTaskActivity.setOnClickListener {
            startActivity<SingleTaskActivity2>(getFlag())
        }
        bind.btnStartSingleInstanceActivity.setOnClickListener {
            startActivity<SingleInstanceActivity2>(getFlag())
        }
        log("onCreate")
    }

    private fun getFlag(): Int {
        val newTaskFlag = if (bind.cbNewTask.isChecked) Intent.FLAG_ACTIVITY_NEW_TASK else 0
        val singleTopFlag = if (bind.cbSingleTop.isChecked) Intent.FLAG_ACTIVITY_SINGLE_TOP else 0
        val clearTopFlag = if (bind.cbClearTop.isChecked) Intent.FLAG_ACTIVITY_CLEAR_TOP else 0
        val clearTaskFlag = if (bind.cbClearTask.isChecked) Intent.FLAG_ACTIVITY_CLEAR_TASK else 0
        return newTaskFlag or singleTopFlag or clearTopFlag or clearTaskFlag
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

class StandardActivity2 : BaseIntentFlagActivity() {

    override fun getTip(): String {
        return "StandardActivity"
    }

}

class SingleTopActivity2 : BaseIntentFlagActivity() {

    override fun getTip(): String {
        return "SingleTopActivity"
    }

}

class SingleTaskActivity2 : BaseIntentFlagActivity() {

    override fun getTip(): String {
        return "SingleTaskActivity"
    }

}

class SingleInstanceActivity2 : BaseIntentFlagActivity() {

    override fun getTip(): String {
        return "SingleInstanceActivity"
    }

}
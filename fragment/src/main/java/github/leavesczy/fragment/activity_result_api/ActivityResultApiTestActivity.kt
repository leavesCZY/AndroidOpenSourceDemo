package github.leavesczy.fragment.activity_result_api

import android.app.Activity
import android.os.Bundle
import github.leavesczy.base.BaseActivity
import github.leavesczy.fragment.databinding.ActivityActivityResultApiTestBinding

/**
 * @Author: leavesCZY
 * @Date: 2021/9/6 16:03
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class ActivityResultApiTestActivity : BaseActivity() {

    override val bind by getBind<ActivityActivityResultApiTestBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.btnFinish.setOnClickListener {
            val userName = GetLocation.getUserName(intent)
            setResult(Activity.RESULT_OK, GetLocation.putResultOk("$userName-广州"))
            finish()
        }
    }

}
package github.leavesc.fragment.activity_result_api

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

/**
 * @Author: leavesC
 * @Date: 2021/9/6 16:03
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class GetLocation : ActivityResultContract<String, String>() {

    companion object {

        private const val KEY_REQ_LOCATION = "keyReqLocation"

        private const val KEY_RES_LOCATION = "keyResLocation"

        fun getUserName(intent: Intent): String {
            return intent.getStringExtra(KEY_REQ_LOCATION)
                ?: throw RuntimeException("userName must not be null")
        }

        fun putResultOk(location: String): Intent {
            return Intent().apply {
                putExtra(KEY_RES_LOCATION, location)
            }
        }

    }

    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, ActivityResultApiTestActivity::class.java)
        intent.putExtra(KEY_REQ_LOCATION, input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (resultCode == Activity.RESULT_OK) {
            return intent?.getStringExtra(KEY_RES_LOCATION)
        }
        return null
    }

}
package github.leavesc.fragment.fragment_result_api

import android.os.Bundle
import github.leavesc.base.BaseActivity
import github.leavesc.fragment.databinding.ActivityFragmentResultApiBinding

/**
 * @Author: leavesC
 * @Date: 2021/9/6 16:01
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class FragmentResultApiActivity : BaseActivity() {

    override val bind by getBind<ActivityFragmentResultApiBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.setFragmentResultListener(
            requestKeyToActivity,
            this,
            { requestKey, result ->
                bind.tvMessage.text = "requestKey: $requestKey \n result: $result"
            })
    }

}
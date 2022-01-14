package github.leavesczy.retrofit

import android.os.Bundle
import androidx.lifecycle.Observer
import github.leavesczy.base.BaseActivity
import github.leavesczy.retrofit.databinding.ActivityLiveDataCallAdapterBinding
import github.leavesczy.retrofit.http.RetrofitManager

/**
 * @Author: leavesCZY
 * @Date: 2020/10/24 12:39
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class LiveDataCallAdapterActivity : BaseActivity() {

    override val bind by getBind<ActivityLiveDataCallAdapterBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.btnSuccess.setOnClickListener {
            RetrofitManager.apiService.getUserDataSuccess().observe(this, Observer {
                if (it.isSuccess) {
                    showToast(it.toString())
                } else {
                    showToast("failed: " + it.msg)
                }
            })
        }
        bind.btnFailed.setOnClickListener {
            RetrofitManager.apiService.getUserDataFailed().observe(this, Observer {
                if (it.isSuccess) {
                    showToast(it.toString())
                } else {
                    showToast("failed: " + it.msg)
                }
            })
        }
    }

}
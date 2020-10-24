package github.leavesc.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import github.leavesc.base.EasyRouterPath
import github.leavesc.easyrouter.annotation.Router
import github.leavesc.retrofit.http.RetrofitManager
import kotlinx.android.synthetic.main.activity_live_data_call_adapter.*

/**
 * 作者：leavesC
 * 时间：2020/10/24 12:39
 * 描述：
 * GitHub：https://github.com/leavesC
 */
@Router(EasyRouterPath.PATH_RETROFIT)
class LiveDataCallAdapterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_call_adapter)
        btn_success.setOnClickListener {
            RetrofitManager.apiService.getUserDataSuccess().observe(this, Observer {
                if (it.isSuccess) {
                    showToast(it.toString())
                } else {
                    showToast("failed: " + it.msg)
                }
            })
        }
        btn_failed.setOnClickListener {
            RetrofitManager.apiService.getUserDataFailed().observe(this, Observer {
                if (it.isSuccess) {
                    showToast(it.toString())
                } else {
                    showToast("failed: " + it.msg)
                }
            })
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}
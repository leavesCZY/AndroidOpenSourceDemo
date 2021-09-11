package github.leavesc.fragment.extend

import android.Manifest
import android.os.Bundle
import github.leavesc.base.BaseActivity
import github.leavesc.fragment.databinding.ActivityFragmentExtendBinding

/**
 * @Author: leavesC
 * @Date: 2021/9/5 17:10
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class FragmentExtendActivity : BaseActivity() {

    override val bind by getBind<ActivityFragmentExtendBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.btnReq.setOnClickListener {
            RequestPermissionsFragment.request(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA
                )
            ) { permissions: Array<String>,
                grantResults: IntArray ->
                permissions.forEachIndexed { index, s ->
                    showToast("permission：" + s + " grantResult：" + grantResults[index])
                }
            }
        }
    }

}
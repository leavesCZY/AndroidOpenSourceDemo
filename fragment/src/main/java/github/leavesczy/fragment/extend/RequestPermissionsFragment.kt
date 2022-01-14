package github.leavesczy.fragment.extend

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit

/**
 * @Author: leavesCZY
 * @Date: 2021/9/5 17:10
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class RequestPermissionsFragment : Fragment() {

    companion object {

        private const val EXTRA_PERMISSIONS = "github.leavesczy.extra.PERMISSIONS"

        private const val REQUEST_CODE = 100

        fun request(
            activity: FragmentActivity,
            permissions: Array<String>,
            onRequestResult: ((Array<String>, IntArray) -> Unit)
        ) {
            val bundle = Bundle()
            bundle.putStringArray(EXTRA_PERMISSIONS, permissions)
            val requestPermissionsFragment = RequestPermissionsFragment()
            requestPermissionsFragment.arguments = bundle
            requestPermissionsFragment.onRequestResult = onRequestResult
            activity.supportFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(null)
                add(requestPermissionsFragment, null)
            }
        }

    }

    private val permissions by lazy {
        requireArguments().getStringArray(EXTRA_PERMISSIONS) ?: throw IllegalArgumentException()
    }

    private var onRequestResult: ((Array<String>, IntArray) -> Unit)? =
        null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requestPermissions(permissions, REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        onRequestResult?.invoke(permissions, grantResults)
        parentFragmentManager.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onRequestResult = null
    }

}
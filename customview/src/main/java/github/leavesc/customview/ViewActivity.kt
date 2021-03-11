package github.leavesc.customview

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import github.leavesc.base.BaseActivity

/**
 * 作者：leavesC
 * 时间：2020/03/12 01:05
 * 描述：
 * GitHub：https://github.com/leavesC
 */
class ViewActivity : BaseActivity() {

    companion object {

        private const val KEY_LAYOUT_ID = "keyLayoutId"

        fun navTo(activity: BaseActivity, @LayoutRes layoutId: Int) {
            val intent = Intent(activity, ViewActivity::class.java)
            intent.putExtra(KEY_LAYOUT_ID, layoutId)
            activity.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(intent.getIntExtra(KEY_LAYOUT_ID, 0))
    }

}
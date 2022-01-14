package github.leavesczy.fragment.fragment_factory

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.commit
import github.leavesczy.base.BaseActivity
import github.leavesczy.fragment.R
import github.leavesczy.fragment.databinding.ActivityFragmentFactoryBinding

/**
 * @Author: leavesCZY
 * @Date: 2021/9/6 17:16
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class FragmentFactoryActivity : BaseActivity() {

    override val bind by getBind<ActivityFragmentFactoryBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        //需要在 super.onCreate 之前调用，因为 Activity 需要依靠 FragmentFactory 来完成 Fragment 的恢复重建
        supportFragmentManager.fragmentFactory = MyFragmentFactory(Color.parseColor("#00BCD4"))
        super.onCreate(savedInstanceState)
        supportFragmentManager.commit {
            add(R.id.fragmentContainerView, FragmentFactoryFragment::class.java, null)
            setReorderingAllowed(true)
            disallowAddToBackStack()
        }
    }

}
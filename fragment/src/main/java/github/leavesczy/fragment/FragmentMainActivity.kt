package github.leavesczy.fragment

import android.os.Bundle
import github.leavesczy.base.BaseActivity
import github.leavesczy.fragment.activity_result_api.ActivityResultApiActivity
import github.leavesczy.fragment.databinding.ActivityFragmentMainBinding
import github.leavesczy.fragment.extend.FragmentExtendActivity
import github.leavesczy.fragment.fragment_factory.FragmentFactoryActivity
import github.leavesczy.fragment.fragment_result_api.FragmentResultApiActivity
import github.leavesczy.fragment.lifecycle.FragmentLifecycleActivity
import github.leavesczy.fragment.viewpager2.ViewPager2Activity

/**
 * @Author: leavesCZY
 * @Date: 2021/9/6 17:48
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class FragmentMainActivity : BaseActivity() {

    override val bind by getBind<ActivityFragmentMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.btnFragmentFactory.setOnClickListener {
            startActivity<FragmentFactoryActivity>()
        }
        bind.btnActivityResultApi.setOnClickListener {
            startActivity<ActivityResultApiActivity>()
        }
        bind.btnFragmentResultApi.setOnClickListener {
            startActivity<FragmentResultApiActivity>()
        }
        bind.btnFragmentLifecycle.setOnClickListener {
            startActivity<FragmentLifecycleActivity>()
        }
        bind.btnViewPager2.setOnClickListener {
            startActivity<ViewPager2Activity>()
        }
        bind.btnExtend.setOnClickListener {
            startActivity<FragmentExtendActivity>()
        }
    }

}
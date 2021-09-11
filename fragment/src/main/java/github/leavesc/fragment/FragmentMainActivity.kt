package github.leavesc.fragment

import android.os.Bundle
import github.leavesc.base.BaseActivity
import github.leavesc.fragment.activity_result_api.ActivityResultApiActivity
import github.leavesc.fragment.databinding.ActivityFragmentMainBinding
import github.leavesc.fragment.extend.FragmentExtendActivity
import github.leavesc.fragment.fragment_factory.FragmentFactoryActivity
import github.leavesc.fragment.fragment_result_api.FragmentResultApiActivity
import github.leavesc.fragment.lifecycle.FragmentLifecycleActivity
import github.leavesc.fragment.viewpager2.ViewPager2Activity

/**
 * @Author: leavesC
 * @Date: 2021/9/6 17:48
 * @Desc:
 * @Github：https://github.com/leavesC
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
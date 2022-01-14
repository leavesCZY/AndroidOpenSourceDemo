package github.leavesczy.fragment.viewpager2

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import github.leavesczy.base.BaseActivity
import github.leavesczy.fragment.databinding.ActivityViewPager2Binding

/**
 * @Author: leavesCZY
 * @Date: 2021/9/7 16:50
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class ViewPager2Activity : BaseActivity() {

    override val bind by getBind<ActivityViewPager2Binding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        bind.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(
            bind.tabs,
            bind.viewPager,
            true
        ) { tab, position ->
            tab.text = sectionsPagerAdapter.getPageTitle(position)
        }.attach()
    }

}
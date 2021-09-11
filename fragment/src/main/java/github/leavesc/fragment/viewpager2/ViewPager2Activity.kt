package github.leavesc.fragment.viewpager2

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import github.leavesc.base.BaseActivity
import github.leavesc.fragment.databinding.ActivityViewPager2Binding

/**
 * @Author: leavesC
 * @Date: 2021/9/7 16:50
 * @Desc:
 * @Github：https://github.com/leavesC
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
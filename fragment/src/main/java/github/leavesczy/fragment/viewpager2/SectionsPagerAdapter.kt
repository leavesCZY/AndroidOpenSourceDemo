package github.leavesczy.fragment.viewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import github.leavesczy.fragment.R

/**
 * @Author: leavesCZY
 * @Date: 2021/9/8 0:29
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class SectionsPagerAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val tabTitles = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_3
    )

    override fun getItemCount(): Int {
        return tabTitles.size
    }

    override fun createFragment(position: Int): Fragment {
        return PageFragment.newInstance(position + 1)
    }

    fun getPageTitle(position: Int): CharSequence {
        return fragmentActivity.resources.getString(tabTitles[position])
    }

}
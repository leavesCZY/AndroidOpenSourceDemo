package github.leavesc.motion_layout

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import github.leavesc.base.BaseActivity
import kotlinx.android.synthetic.main.activity_motion_layout_demo_aactivity.*

/**
 * @Author: leavesC
 * @Date: 2020/12/27 15:29
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class MotionLayoutDemoBActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_layout_demo_bactivity)
        val fragmentAdapter = FunFragmentAdapter(this)
        viewPager.adapter = FunFragmentAdapter(this)
        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = fragmentAdapter.getTitle(position)
        }
        tabLayoutMediator.attach()
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val progress = (position + positionOffset) / (fragmentAdapter.itemCount - 1)
                motion_layout.progress = progress
            }
        })
    }

}
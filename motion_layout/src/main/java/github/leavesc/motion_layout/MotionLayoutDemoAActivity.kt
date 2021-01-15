package github.leavesc.motion_layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
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
open class MotionLayoutDemoAActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId())
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

    protected open fun getContentViewId(): Int {
        return R.layout.activity_motion_layout_demo_aactivity
    }

}

class FunFragmentAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return FunFragment()
    }

    fun getTitle(position: Int): String {
        return when (position) {
            0 -> {
                "飞跃"
            }
            1 -> {
                "星空"
            }
            else -> {
                "火箭"
            }
        }
    }

}

class FunFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_fun, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = MyRecyclerViewAdapter()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        return view
    }

    open class MyRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return object : RecyclerView.ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
            ) {

            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return 10
        }

    }

}
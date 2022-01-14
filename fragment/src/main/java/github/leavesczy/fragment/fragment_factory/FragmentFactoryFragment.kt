package github.leavesczy.fragment.fragment_factory

import android.os.Bundle
import android.view.View
import github.leavesczy.fragment.R
import github.leavesczy.fragment.base.BaseFragment

/**
 * @Author: leavesCZY
 * @Date: 2021/9/6 17:48
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class FragmentFactoryFragment(private val bgColor: Int) :
    BaseFragment(R.layout.fragment_fragment_factory) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val flRoot = view.findViewById<View>(R.id.flRoot)
        flRoot.setBackgroundColor(bgColor)
    }

}
package github.leavesc.fragment.fragment_factory

import android.os.Bundle
import android.view.View
import github.leavesc.fragment.R
import github.leavesc.fragment.base.BaseFragment

/**
 * @Author: leavesC
 * @Date: 2021/9/6 17:48
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class FragmentFactoryFragment(private val bgColor: Int) :
    BaseFragment(R.layout.fragment_fragment_factory) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val flRoot = view.findViewById<View>(R.id.flRoot)
        flRoot.setBackgroundColor(bgColor)
    }

}
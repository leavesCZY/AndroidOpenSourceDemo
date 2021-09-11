package github.leavesc.fragment.lifecycle

import android.os.Bundle
import android.view.View
import android.widget.TextView
import github.leavesc.fragment.R
import github.leavesc.fragment.base.BaseFragment

/**
 * @Author: leavesC
 * @Date: 2021/9/6 17:09
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class FragmentLifecycleFragment : BaseFragment(R.layout.fragment_fragment_lifecycle) {

    companion object {

        private const val KEY_TAG = "keyTag"

        private const val KEY_BG_COLOR = "keyBgColor"

        fun newInstance(fragmentTag: String, bgColor: Int) =
            FragmentLifecycleFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TAG, fragmentTag)
                    putInt(KEY_BG_COLOR, bgColor)
                }
            }
    }

    private val fragmentTag by lazy {
        arguments?.getString(KEY_TAG) ?: throw IllegalArgumentException()
    }

    private val bgColor by lazy {
        arguments?.getInt(KEY_BG_COLOR) ?: throw IllegalArgumentException()
    }

    override val logTag: String
        get() = "Fragment-$fragmentTag"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val llRoot = view.findViewById<View>(R.id.llRoot)
        val tvTag = view.findViewById<TextView>(R.id.tvTag)
        llRoot.setBackgroundColor(bgColor)
        tvTag.text = fragmentTag
    }

}
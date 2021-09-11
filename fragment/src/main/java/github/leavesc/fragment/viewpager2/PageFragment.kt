package github.leavesc.fragment.viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import github.leavesc.fragment.base.BaseFragment
import github.leavesc.fragment.databinding.FragmentPageBinding

/**
 * @Author: leavesC
 * @Date: 2021/9/7 16:53
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class PageFragment : BaseFragment() {

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): PageFragment {
            return PageFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }

    }

    private lateinit var pageViewModel: PageViewModel

    private var _binding: FragmentPageBinding? = null

    private val binding get() = _binding!!

    private val sectionNumber by lazy {
        arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
    }

    override val logTag: String
        get() = "PageFragment-$sectionNumber"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(sectionNumber)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentPageBinding.inflate(inflater, container, false)
        pageViewModel.text.observe(viewLifecycleOwner, {
            binding.sectionLabel.text = it
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
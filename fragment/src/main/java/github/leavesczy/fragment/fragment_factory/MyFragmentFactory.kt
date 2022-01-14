package github.leavesczy.fragment.fragment_factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

/**
 * @Author: leavesCZY
 * @Date: 2021/9/4 16:43
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class MyFragmentFactory(private val bgColor: Int) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val clazz = loadFragmentClass(classLoader, className)
        if (clazz == FragmentFactoryFragment::class.java) {
            return FragmentFactoryFragment(bgColor)
        }
        return super.instantiate(classLoader, className)
    }

}
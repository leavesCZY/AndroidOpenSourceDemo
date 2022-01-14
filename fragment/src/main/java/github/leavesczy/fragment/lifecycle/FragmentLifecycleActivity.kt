package github.leavesczy.fragment.lifecycle

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import github.leavesczy.base.BaseActivity
import github.leavesczy.fragment.R
import github.leavesczy.fragment.databinding.ActivityFragmentLifecycleBinding

/**
 * @Author: leavesCZY
 * @Date: 2021/9/6 17:09
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class FragmentLifecycleActivity : BaseActivity() {

    override val logTag: String
        get() = "Lifecycle-Activity"

    override val bind by getBind<ActivityFragmentLifecycleBinding>()

    private var tagIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.btnAdd.setOnClickListener {
            supportFragmentManager.commit {
                defaultAction()
                add(
                    R.id.fragmentContainerView, FragmentLifecycleFragment.newInstance(
                        fragmentTag = (tagIndex++).toString(),
                        bgColor = Color.parseColor("#0091EA")
                    )
                )
            }
        }
        bind.btnRemove.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
            if (fragment != null) {
                supportFragmentManager.commit {
                    defaultAction()
                    remove(
                        fragment
                    )
                }
            }
        }
        bind.btnReplace.setOnClickListener {
            supportFragmentManager.commit {
                defaultAction()
                replace(
                    R.id.fragmentContainerView, FragmentLifecycleFragment.newInstance(
                        fragmentTag = (tagIndex++).toString(),
                        bgColor = Color.parseColor("#009688")
                    )
                )
            }
        }
        bind.btnPopBackStack.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }

    private fun FragmentTransaction.defaultAction() {
        setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out
        )
        setReorderingAllowed(true)
        if (bind.switchAddToBackStack.isChecked) {
            addToBackStack(null)
        } else {
            disallowAddToBackStack()
        }
    }

}
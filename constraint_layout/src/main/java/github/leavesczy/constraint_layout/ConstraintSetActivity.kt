package github.leavesczy.constraint_layout

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import github.leavesczy.base.BaseActivity
import github.leavesczy.constraint_layout.databinding.ActivityConstraintSetBinding

/**
 * @Author: leavesCZY
 * @Date: 2020/12/26 23:02
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class ConstraintSetActivity : BaseActivity() {

    companion object {

        private const val SHOW_BIG_IMAGE = "showBigImage"

    }

    override val bind by getBind<ActivityConstraintSetBinding>()

    private var showBigImage = false

    private val constraintSetNormal = ConstraintSet()

    private val constraintSetBig = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //获取初始的约束集
        constraintSetNormal.clone(bind.clRootView)
        //加载目标约束集
        constraintSetBig.load(this, R.layout.activity_constraint_set_big)
        if (savedInstanceState != null) {
            val previous = savedInstanceState.getBoolean(SHOW_BIG_IMAGE)
            if (previous != showBigImage) {
                showBigImage = previous
                applyConfig()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(SHOW_BIG_IMAGE, showBigImage)
    }

    fun toggleMode(view: View) {
        TransitionManager.beginDelayedTransition(bind.clRootView)
        showBigImage = !showBigImage
        applyConfig()
    }

    //将约束集应用到控件上
    private fun applyConfig() {
        if (showBigImage) {
            constraintSetBig.applyTo(bind.clRootView)
        } else {
            constraintSetNormal.applyTo(bind.clRootView)
        }
    }

}
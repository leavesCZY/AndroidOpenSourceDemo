package github.leavesczy.constraint_layout

import android.os.Bundle
import github.leavesczy.base.BaseActivity
import github.leavesczy.constraint_layout.databinding.ActivityConstraintLayoutMainBinding

/**
 * @Author: leavesCZY
 * @Date: 2020/12/26 21:59
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class ConstraintLayoutMainActivity : BaseActivity() {

    override val bind by getBind<ActivityConstraintLayoutMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.btnFlow.setOnClickListener {
            startActivity(FlowActivity::class.java)
        }
        bind.btnLayer.setOnClickListener {
            startActivity(LayerActivity::class.java)
        }
        bind.btnConstraintSet.setOnClickListener {
            startActivity(ConstraintSetActivity::class.java)
        }
        bind.btnCircularRevealHelper.setOnClickListener {
            startActivity(ConstraintHelperActivity::class.java)
        }
        bind.btnImageFilterView.setOnClickListener {
            startActivity(ImageFilterViewActivity::class.java)
        }
    }

}
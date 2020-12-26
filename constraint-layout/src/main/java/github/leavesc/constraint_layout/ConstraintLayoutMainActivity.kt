package github.leavesc.constraint_layout

import android.os.Bundle
import github.leavesc.base.BaseActivity
import kotlinx.android.synthetic.main.activity_constraint_layout_main.*

/**
 * @Author: leavesC
 * @Date: 2020/12/26 21:59
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class ConstraintLayoutMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout_main)
        btn_flow.setOnClickListener {
            startActivity(FlowActivity::class.java)
        }
        btn_layer.setOnClickListener {
            startActivity(LayerActivity::class.java)
        }
        btn_constraintSet.setOnClickListener {
            startActivity(ConstraintSetActivity::class.java)
        }
    }

}
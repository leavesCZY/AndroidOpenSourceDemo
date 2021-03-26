package github.leavesc.constraint_layout

import android.os.Bundle
import github.leavesc.base.BaseActivity
import github.leavesc.constraint_layout.databinding.ActivityFlowBinding

/**
 * @Author: leavesC
 * @Date: 2020/12/26 22:01
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class FlowActivity : BaseActivity() {

    override val bind by getBind<ActivityFlowBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
    }

}
package github.leavesczy.constraint_layout

import android.os.Bundle
import github.leavesczy.base.BaseActivity
import github.leavesczy.constraint_layout.databinding.ActivityFlowBinding

/**
 * @Author: leavesCZY
 * @Date: 2020/12/26 22:01
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class FlowActivity : BaseActivity() {

    override val bind by getBind<ActivityFlowBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
    }

}
package github.leavesc.demo

import android.os.Bundle
import github.leavesc.base.EasyRouterPath
import github.leavesc.coil.CoilMainActivity
import github.leavesc.constraint_layout.ConstraintLayoutMainActivity
import github.leavesc.demo.base.BaseActivity
import github.leavesc.demo.easyeventbus.EasyEventBusActivity
import github.leavesc.easyrouter.EasyRouter
import github.leavesc.glide.GlideActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 作者：leavesC
 * 时间：2020/10/2 13:14
 * 描述：
 * GitHub：https://github.com/leavesC
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_easyEventBus.setOnClickListener {
            startActivity(EasyEventBusActivity::class.java)
        }
        btn_easyRouter.setOnClickListener {
            EasyRouter.navigation(EasyRouterPath.PATH_HOME)
        }
        btn_liveDataCallAdapter.setOnClickListener {
            EasyRouter.navigation(EasyRouterPath.PATH_RETROFIT)
        }
        btn_glide.setOnClickListener {
            startActivity(GlideActivity::class.java)
        }
        btn_coil.setOnClickListener {
            startActivity(CoilMainActivity::class.java)
        }
        btn_constraintLayout.setOnClickListener {
            startActivity(ConstraintLayoutMainActivity::class.java)
        }
    }

}
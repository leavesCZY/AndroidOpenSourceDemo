package github.leavesc.demo.easyrouter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import github.leavesc.base.EasyRouterPath
import github.leavesc.demo.R
import github.leavesc.easyrouter.EasyRouter
import github.leavesc.easyrouter.annotation.Router
import kotlinx.android.synthetic.main.activity_easy_router_home.*

/**
 * 作者：leavesC
 * 时间：2020/10/5 22:46
 * 描述：
 * GitHub：https://github.com/leavesC
 */
@Router(EasyRouterPath.PATH_HOME)
class EasyRouterHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_router_home)
        btn_navToSubPage.setOnClickListener {
            EasyRouter.navigation(EasyRouterPath.PATH_SUB_PAGE)
        }
        btn_navToTestAPage.setOnClickListener {
            EasyRouter.navigation(EasyRouterPath.PATH_TEST_A)
        }
        btn_navToNothing.setOnClickListener {
            EasyRouter.navigation("test/nothing")
        }
    }

}
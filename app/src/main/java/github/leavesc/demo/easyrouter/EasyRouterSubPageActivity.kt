package github.leavesc.demo.easyrouter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import github.leavesc.base.EasyRouterPath
import github.leavesc.demo.R
import github.leavesc.easyrouter.annotation.Router

/**
 * 作者：leavesC
 * 时间：2020/10/5 23:43
 * 描述：
 * GitHub：https://github.com/leavesC
 */
@Router(EasyRouterPath.PATH_SUB_PAGE)
class EasyRouterSubPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_router_sub_page)
    }

}
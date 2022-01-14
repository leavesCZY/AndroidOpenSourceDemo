package github.leavesczy.easyeventbus_demo

import android.os.Bundle
import github.leavesczy.base.BaseActivity
import github.leavesczy.base.showToast
import github.leavesczy.easyeventbus_api.EasyEventBus
import github.leavesczy.easyeventbus_api.Event
import github.leavesczy.easyeventbus_demo.databinding.ActivityEasyEventBusMainBinding

/**
 * @Author: leavesCZY
 * @Date: 2021/1/15 23:42
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class EasyEventBusMainActivity : BaseActivity() {

    override val bind by getBind<ActivityEasyEventBusMainBinding>()

    private val eventTest = EasyEventBusTest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EasyEventBus.register(this)
        eventTest.register()
        bind.btnPostString.setOnClickListener {
            EasyEventBus.post("Hello")
        }
        bind.btnPostBean.setOnClickListener {
            EasyEventBus.post(HelloBean("hi"))
        }
    }

    @Event
    fun stringFun(msg: String) {
        showToast("$msg ${this.javaClass.simpleName}")
    }

    @Event
    fun benFun(msg: HelloBean) {
        showToast("${msg.data} ${this.javaClass.simpleName}")
    }

    override fun onDestroy() {
        super.onDestroy()
        EasyEventBus.unregister(this)
        eventTest.unregister()
    }

}

class EasyEventBusTest {

    @Event
    fun stringFun(msg: String) {
        showToast("$msg ${this.javaClass.simpleName}")
    }

    @Event
    fun benFun(msg: HelloBean) {
        showToast("${msg.data} ${this.javaClass.simpleName}")
    }

    fun register() {
        EasyEventBus.register(this)
    }

    fun unregister() {
        EasyEventBus.unregister(this)
    }

}

data class HelloBean(val data: String)
package github.leavesc.easyeventbus_demo

import android.os.Bundle
import github.leavesc.base.BaseActivity
import github.leavesc.base.showToast
import github.leavesc.easyeventbus_api.EasyEventBus
import github.leavesc.easyeventbus_api.Event
import github.leavesc.easyeventbus_demo.databinding.ActivityEasyEventBusMainBinding

/**
 * 作者：leavesC
 * 时间：2021/1/15 23:42
 * 描述：
 * GitHub：https://github.com/leavesC
 */
class EasyEventBusMainActivity : BaseActivity() {

    override val bind by getBind<ActivityEasyEventBusMainBinding>()

    private val eventTest = EasyBusEventTest()

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

class EasyBusEventTest {

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
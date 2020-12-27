package github.leavesc.demo.easyeventbus

import android.os.Bundle
import github.leavesc.base.BaseActivity
import github.leavesc.demo.R
import github.leavesc.demo.utils.showToast
import github.leavesc.easyeventbus.EasyEventBus
import github.leavesc.easyeventbus.Event
import kotlinx.android.synthetic.main.activity_easy_event_bus.*

/**
 * 作者：leavesC
 * 时间：2020/10/2 13:14
 * 描述：
 * GitHub：https://github.com/leavesC
 */
class EasyEventBusActivity : BaseActivity() {

    private val eventTest = EasyBusEventTest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_event_bus)
        EasyEventBus.register(this)
        eventTest.register()

        btn_postString.setOnClickListener {
            EasyEventBus.post("Hello")
        }
        btn_postBean.setOnClickListener {
            EasyEventBus.post(HelloBean("hi"))
        }
    }

    @Event
    fun stringFun(msg: String) {
        showToast("$msg EasyEventBusActivity")
    }

    @Event
    fun benFun(msg: HelloBean) {
        showToast("${msg.data} EasyEventBusActivity")
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
        showToast("$msg EasyBusEventTest")
    }

    @Event
    fun benFun(msg: HelloBean) {
        showToast("${msg.data} EasyBusEventTest")
    }

    fun register() {
        EasyEventBus.register(this)
    }

    fun unregister() {
        EasyEventBus.unregister(this)
    }

}

data class HelloBean(val data: String)
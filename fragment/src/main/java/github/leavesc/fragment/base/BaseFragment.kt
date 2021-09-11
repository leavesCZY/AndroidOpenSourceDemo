package github.leavesc.fragment.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @Author: leavesC
 * @Date: 2021/9/5 23:42
 * @Desc:
 * @Github：https://github.com/leavesC
 */
open class BaseFragment(layoutId: Int = 0) : Fragment(layoutId) {

    protected open val logTag: String
        get() = javaClass.simpleName

    override fun onAttach(context: Context) {
        log("onAttach-context-start")
        super.onAttach(context)
        log("onAttach-context-end")
    }

    override fun onAttach(activity: Activity) {
        log("onAttach-activity-start")
        super.onAttach(activity)
        log("onAttach-activity-end")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        log("onCreate-start")
        super.onCreate(savedInstanceState)
        log("onCreate-end")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        log("onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        log("onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        log("onActivityCreated-start")
        super.onActivityCreated(savedInstanceState)
        log("onActivityCreated-end")
    }

    override fun onStart() {
        log("onStart-start")
        super.onStart()
        log("onStart-end")
    }

    override fun onResume() {
        log("onResume-start")
        super.onResume()
        log("onResume-end")
    }

    override fun onPause() {
        log("onPause-start")
        super.onPause()
        log("onPause-end")
    }

    override fun onStop() {
        log("onStop-start")
        super.onStop()
        log("onStop-end")
    }

    override fun onDestroyView() {
        log("onDestroyView-start")
        super.onDestroyView()
        log("onDestroyView-end")
    }

    override fun onDestroy() {
        log("onDestroy-start")
        super.onDestroy()
        log("onDestroy-end")
    }

    override fun onDetach() {
        log("onDetach-start")
        super.onDetach()
        log("onDetach-end")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        log("onSaveInstanceState-start")
        super.onSaveInstanceState(outState)
        log("onSaveInstanceState-end")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        log("onConfigurationChanged-start")
        super.onConfigurationChanged(newConfig)
        log("onConfigurationChanged-end")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        log("onViewStateRestored-start")
        super.onViewStateRestored(savedInstanceState)
        log("onViewStateRestored-end")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        log("onHiddenChanged-start")
        super.onHiddenChanged(hidden)
        log("onHiddenChanged-end")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        log("onActivityResult-start")
        super.onActivityResult(requestCode, resultCode, data)
        log("onActivityResult-end")
    }

    protected fun log(log: String) {
        Log.e(logTag, log)
    }

}
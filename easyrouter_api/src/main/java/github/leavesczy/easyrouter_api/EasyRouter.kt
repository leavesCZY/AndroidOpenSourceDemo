package github.leavesczy.easyrouter_api

import android.app.Application
import android.content.Intent
import github.leavesczy.base.showToast
import github.leavesczy.easyrouter_annotation.RouterBean

/**
 * @Author: leavesCZY
 * @Date: 2020/10/5 23:45
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
object EasyRouter {

    private const val PACKAGE_NAME = "github.leavesczy.easyrouter"

    private lateinit var context: Application

    private val routerByGroupMap = hashMapOf<String, Map<String, RouterBean>>()

    fun init(application: Application) {
        context = application
    }

    fun navigation(path: String) {
        val routerBean = getRouterLoader(path)
        if (routerBean == null) {
            showToast("找不到匹配的路径：$path")
            return
        }
        val intent = Intent(context, routerBean.targetClass)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private fun getRouterLoader(path: String): RouterBean? {
        val group = path.substring(0, path.indexOf("/"))
        val map = routerByGroupMap[group]
        if (map == null) {
            var routerMap: Map<String, RouterBean>? = null
            try {
                val classPath = PACKAGE_NAME + "." + "EasyRouter" + group + "Loader"
                val clazz = Class.forName(classPath)
                val instance = clazz.newInstance()
                val routerMapField = clazz.getDeclaredField("routerMap")
                routerMap =
                    (routerMapField.get(instance) as? Map<String, RouterBean>) ?: hashMapOf()
                routerByGroupMap[group] = routerMap
            } catch (e: Throwable) {
                e.printStackTrace()
            } finally {
                if (routerMap == null) {
                    routerByGroupMap[group] = hashMapOf()
                }
            }
        }
        return routerByGroupMap[group]?.get(path)
    }

}
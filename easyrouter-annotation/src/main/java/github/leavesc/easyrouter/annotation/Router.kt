package github.leavesc.easyrouter.annotation

/**
 * 作者：leavesC
 * 时间：2020/10/6 1:08
 * 描述：
 * GitHub：https://github.com/leavesC
 */
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class Router(val path: String)
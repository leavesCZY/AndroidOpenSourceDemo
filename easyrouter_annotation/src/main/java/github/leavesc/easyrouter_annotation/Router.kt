package github.leavesc.easyrouter_annotation

/**
 * @Author: leavesC
 * @Date: 2020/10/6 1:08
 * @Desc:
 * @Github：https://github.com/leavesC
 */
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class Router(val path: String)
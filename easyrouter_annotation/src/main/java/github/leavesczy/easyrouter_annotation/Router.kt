package github.leavesczy.easyrouter_annotation

/**
 * @Author: leavesCZY
 * @Date: 2020/10/6 1:08
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class Router(val path: String)
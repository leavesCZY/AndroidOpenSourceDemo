package github.leavesczy.retrofit.http

import androidx.lifecycle.LiveData
import retrofit2.http.GET

/**
 * @Author: leavesCZY
 * @Date: 2020/10/22 20:48
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
data class UserBean(val userName: String, val userAge: Int)

interface ApiService {

    @GET("getUserDataSuccess")
    fun getUserDataSuccess(): LiveData<HttpWrapBean<UserBean>>

    @GET("getUserDataFailed")
    fun getUserDataFailed(): LiveData<HttpWrapBean<UserBean>>

}
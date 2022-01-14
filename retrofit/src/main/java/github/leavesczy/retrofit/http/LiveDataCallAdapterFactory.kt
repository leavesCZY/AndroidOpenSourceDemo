package github.leavesczy.retrofit.http

import androidx.lifecycle.LiveData
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @Author: leavesCZY
 * @Date: 2020/10/22 20:53
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class LiveDataCallAdapterFactory private constructor() : CallAdapter.Factory() {

    companion object {

        fun create(): LiveDataCallAdapterFactory {
            return LiveDataCallAdapterFactory()
        }

    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) {
            //并非目标类型的话就直接返回 null
            return null
        }
        //拿到 LiveData 包含的内部泛型类型
        val responseType = getParameterUpperBound(0, returnType as ParameterizedType)
        require(getRawType(responseType) == HttpWrapBean::class.java) {
            "LiveData 包含的泛型类型必须是 HttpWrapBean"
        }
        return LiveDataCallAdapter<Any>(responseType)
    }

}
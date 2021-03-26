package github.leavesc.coil

import android.app.Application
import android.content.Context
import coil.Coil
import coil.ComponentRegistry
import coil.ImageLoader
import coil.request.CachePolicy
import github.leavesc.coil.fetcher.OkHttpFetcher
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

/**
 * @Author: leavesC
 * @Date: 2020/11/22 13:06
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
object CoilHolder {

    fun init(application: Application) {
        val okHttpClient = createOkHttp(application)
        Coil.setImageLoader(
            ImageLoader.Builder(application)
//                .placeholder(ActivityCompat.getDrawable(application, R.drawable.icon_loading))
//                .error(ActivityCompat.getDrawable(application, R.drawable.icon_error))
                .memoryCachePolicy(CachePolicy.ENABLED)
                .callFactory(okHttpClient)
                .componentRegistry(
                    ComponentRegistry.Builder()
//                        .add(VolleyFetcher(application))
                        .add(OkHttpFetcher(okHttpClient)).build()
                )
                .build()
        )
    }

    private fun createOkHttp(application: Application): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(createDefaultCache(application))
            .build()
    }

    private fun createDefaultCache(context: Context): Cache {
        val cacheDirectory = getDefaultCacheDirectory(context)
        return Cache(cacheDirectory, 10 * 1024 * 1024)
    }

    private fun getDefaultCacheDirectory(context: Context): File {
        return File(context.cacheDir, "image_cache").apply { mkdirs() }
    }

}


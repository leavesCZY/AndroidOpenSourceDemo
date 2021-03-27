package github.leavesc.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import github.leavesc.glide.net.OkHttpUrlLoader
import github.leavesc.glide.net.ProgressInterceptor
import okhttp3.OkHttpClient
import java.io.InputStream

/**
 * @Author: leavesC
 * @Date: 2020/11/5 23:16
 * @Desc:
 * @Github：https://github.com/leavesC
 */
@GlideModule
class MyAppGlideModule : AppGlideModule() {

    //用于控制是否需要从 Manifest 文件中解析配置文件
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDiskCache(
            //配置磁盘缓存目录和最大缓存
            DiskLruCacheFactory(
                (context.externalCacheDir ?: context.cacheDir).absolutePath,
                "imageCache",
                1024 * 1024 * 50
            )
        )
        builder.setDefaultRequestOptions {
            return@setDefaultRequestOptions RequestOptions()
                .placeholder(android.R.drawable.ic_menu_upload_you_tube)
                .error(android.R.drawable.ic_menu_call)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .format(DecodeFormat.DEFAULT)
                .encodeQuality(90)
        }
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val okHttpClient =
            OkHttpClient.Builder().addInterceptor(ProgressInterceptor()).build()
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(okHttpClient)
        )
    }

}
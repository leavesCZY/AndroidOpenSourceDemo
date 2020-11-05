package github.leavesc.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import github.leavesc.glide.net.OkHttpUrlLoader
import okhttp3.OkHttpClient
import java.io.InputStream

/**
 * 作者：leavesC
 * 时间：2020/11/5 23:16
 * 描述：
 * GitHub：https://github.com/leavesC
 */
@GlideModule
class MyAppGlideModule : AppGlideModule() {

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {

    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(OkHttpClient())
        )
    }

}
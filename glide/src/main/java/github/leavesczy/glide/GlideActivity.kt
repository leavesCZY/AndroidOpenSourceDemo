package github.leavesczy.glide

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import github.leavesczy.base.BaseActivity
import github.leavesczy.glide.databinding.ActivityGlideBinding
import github.leavesczy.glide.net.ProgressResponseBody
import github.leavesczy.glide.net.TokenGlideUrl

/**
 * @Author: leavesCZY
 * @Date: 2020/11/5 23:00
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class GlideActivity : BaseActivity() {

    override val bind by getBind<ActivityGlideBinding>()

    private val url =
        "https://images.pexels.com/photos/5177790/pexels-photo-5177790.jpeg?auto=compress&token=tokenValue"

    private val progressListener = object : ProgressResponseBody.ProgressListener {
        override fun update(progress: Int) {
            bind.progressBar.progress = progress
            bind.tvProgress.text = progress.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProgressResponseBody.addProgressListener(url, progressListener)
        bind.btnLoadTokenUrl.setOnClickListener {
            Glide.with(this).load(TokenGlideUrl(url))
                .placeholder(android.R.drawable.presence_away)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(bind.ivTokenUrl)
        }
        bind.btnDownloadImage.setOnClickListener {
            Glide.with(this)
                .asBitmap()
                .load(url)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {
                        showToast("onLoadCleared")
                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        bind.ivTokenUrl.setImageBitmap(resource)
                    }
                })
        }
    }

}
package github.leavesc.glide

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import github.leavesc.glide.net.ProgressResponseBody
import github.leavesc.glide.net.TokenGlideUrl
import kotlinx.android.synthetic.main.activity_glide.*

/**
 * 作者：leavesC
 * 时间：2020/11/5 23:00
 * 描述：
 * GitHub：https://github.com/leavesC
 */
class GlideActivity : AppCompatActivity() {

    private val url =
        "https://images.pexels.com/photos/1425174/pexels-photo-1425174.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260&token=tokenValue"

    private val progressListener = object : ProgressResponseBody.ProgressListener {
        override fun update(progress: Int) {
            Log.e("GlideActivity", "progressBar-progress: " + progress)
            progressBar.progress = progress
            tv_progress.text = progress.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)
        ProgressResponseBody.addProgressListener(url, progressListener)
        btn_loadTokenUrl.setOnClickListener {
            Glide.with(this).load(TokenGlideUrl(url))
                .placeholder(android.R.drawable.presence_away)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(iv_tokenUrl)
        }
    }

}
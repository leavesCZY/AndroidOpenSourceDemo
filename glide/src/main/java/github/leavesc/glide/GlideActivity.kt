package github.leavesc.glide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_glide.*

/**
 * 作者：leavesC
 * 时间：2020/11/5 23:00
 * 描述：
 * GitHub：https://github.com/leavesC
 */
class GlideActivity : AppCompatActivity() {

    private val url =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603967458407&di=a6d1039f64bb781b5f366b3ede746f3b&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F0%2F54cae8f16b696.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)
        btn_load.setOnClickListener {
            Glide.with(this).load(url).into(iv_view)
        }
    }

}
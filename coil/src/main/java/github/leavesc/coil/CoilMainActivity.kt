package github.leavesc.coil

import android.graphics.Color
import android.os.Bundle
import coil.load
import github.leavesc.base.BaseActivity
import github.leavesc.coil.databinding.ActivityCoilMainBinding
import github.leavesc.coil.transformation.ColorFilterTransformation
import github.leavesc.coil.transformation.WatermarkTransformation

/**
 * @Author: leavesC
 * @Date: 2020/11/22 11:51
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
class CoilMainActivity : BaseActivity() {

    private val imageUrl =
        "https://t7.baidu.com/it/u=1298946193,2927992282&fm=193&f=GIF"

    override val bind by getBind<ActivityCoilMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.btnLoad.setOnClickListener {
            bind.imageView.load(imageUrl) {
                transformations(
                    WatermarkTransformation("业志陈", Color.parseColor("#8D3700B3"), 120f),
                    ColorFilterTransformation(Color.parseColor("#9CF44336"))
                )
            }
        }
    }

}
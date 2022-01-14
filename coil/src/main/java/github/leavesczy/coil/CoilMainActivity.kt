package github.leavesczy.coil

import android.graphics.Color
import android.os.Bundle
import coil.load
import github.leavesczy.base.BaseActivity
import github.leavesczy.coil.databinding.ActivityCoilMainBinding
import github.leavesczy.coil.transformation.ColorFilterTransformation
import github.leavesczy.coil.transformation.WatermarkTransformation

/**
 * @Author: leavesCZY
 * @Date: 2020/11/22 11:51
 * @Github：https://github.com/leavesCZY
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
package github.leavesc.coil

import android.graphics.Color
import android.os.Bundle
import coil.load
import github.leavesc.base.BaseActivity
import github.leavesc.coil.transformation.ColorFilterTransformation
import github.leavesc.coil.transformation.WatermarkTransformation
import kotlinx.android.synthetic.main.activity_coil_main.*

/**
 * @Author: leavesC
 * @Date: 2020/11/22 11:51
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
class CoilMainActivity : BaseActivity() {

    private val imageUrl =
        "https://t7.baidu.com/it/u=1298946193,2927992282&fm=193&f=GIF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coil_main)
        btn_load.setOnClickListener {
            imageView.load(imageUrl) {
                transformations(
                    WatermarkTransformation("业志陈", Color.parseColor("#8D3700B3"), 120f),
                    ColorFilterTransformation(Color.parseColor("#9CF44336"))
                )
            }
        }
    }

}
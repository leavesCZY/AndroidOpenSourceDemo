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
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606024260370&di=3cf86afce4f2fb2ebac37488b966edc7&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2F201503%2F19%2F211608ztcq7higicydxhsy.jpg"

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
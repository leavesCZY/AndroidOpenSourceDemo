package github.leavesc.constraint_layout

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.constraintlayout.helper.widget.Layer
import github.leavesc.base.BaseActivity
import kotlinx.android.synthetic.main.activity_layer.*
import kotlin.math.abs
import kotlin.math.sin

/**
 * @Author: leavesC
 * @Date: 2020/12/26 22:06
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class LayerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layer)
        btn_test.setOnClickListener {
            val layer = findViewById<Layer>(R.id.layer)
            val animator = ValueAnimator.ofFloat(0f, 360f)
            animator.addUpdateListener { animation ->
                val angle = animation.animatedValue as Float
                layer.rotation = angle
                layer.scaleX = 1 + (180 - abs(angle - 180)) / 20f
                layer.scaleY = 1 + (180 - abs(angle - 180)) / 20f
                val translationX = 500 * sin(Math.toRadians((angle * 5).toDouble())).toFloat()
                val translationY = 500 * sin(Math.toRadians((angle * 7).toDouble())).toFloat()
                layer.translationX = translationX
                layer.translationY = translationY
            }
            animator.duration = 6000
            animator.start()
        }
    }

}
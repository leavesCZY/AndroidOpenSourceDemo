package github.leavesc.constraint_layout

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image_filter_button.*

/**
 * @Author: leavesC
 * @Date: 2020/12/27 0:17
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class ImageFilterButtonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_filter_button)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val realProgress = (progress / 100.0 * 1.0).toFloat()
                imageView.roundPercent = realProgress
                imageView.saturation = realProgress
                imageView.brightness = realProgress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

}
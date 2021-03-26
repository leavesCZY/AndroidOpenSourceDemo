package github.leavesc.constraint_layout

import android.os.Bundle
import android.widget.SeekBar
import github.leavesc.base.BaseActivity
import kotlinx.android.synthetic.main.activity_image_filter_view.*

/**
 * @Author: leavesC
 * @Date: 2020/12/27 0:17
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class ImageFilterViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_filter_view)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val realProgress = (progress / 100.0).toFloat()

                    imageView1.saturation = realProgress * 20
                    imageView2.brightness = 1 - realProgress

                    imageView3.warmth = realProgress * 20
                    imageView4.contrast = realProgress * 2

                    imageView5.round = realProgress * 40
                    imageView6.roundPercent = realProgress

//                    imageView7.crossfade = 1f
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

}
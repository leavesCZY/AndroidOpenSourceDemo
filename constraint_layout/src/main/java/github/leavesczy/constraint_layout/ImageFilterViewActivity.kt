package github.leavesczy.constraint_layout

import android.os.Bundle
import android.widget.SeekBar
import github.leavesczy.base.BaseActivity
import github.leavesczy.constraint_layout.databinding.ActivityImageFilterViewBinding

/**
 * @Author: leavesCZY
 * @Date: 2020/12/27 0:17
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class ImageFilterViewActivity : BaseActivity() {

    override val bind by getBind<ActivityImageFilterViewBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val realProgress = (progress / 100.0).toFloat()

                    bind.imageView1.saturation = realProgress * 20
                    bind.imageView2.brightness = 1 - realProgress

                    bind.imageView3.warmth = realProgress * 20
                    bind.imageView4.contrast = realProgress * 2

                    bind.imageView5.round = realProgress * 40
                    bind.imageView6.roundPercent = realProgress

//                     bind.imageView7.crossfade = 1f
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

}
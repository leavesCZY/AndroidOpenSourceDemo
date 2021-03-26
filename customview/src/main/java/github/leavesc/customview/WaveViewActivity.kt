package github.leavesc.customview

import android.os.Bundle
import android.widget.SeekBar
import github.leavesc.base.BaseActivity
import github.leavesc.customview.databinding.ActivityWaveViewBinding
import github.leavesc.customview.utils.Utils
import github.leavesc.customview.widget.OnSeekBarChangeSimpleListener

/**
 * @Author: leavesC
 * @Date: 2020/03/12 01:05
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
class WaveViewActivity : BaseActivity() {

    override val bind by getBind<ActivityWaveViewBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.seekBarWidth.max = 100
        bind.seekBarWidth.setOnSeekBarChangeListener(object : OnSeekBarChangeSimpleListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val scale = progress / 100.0f
                bind.waveView.waveWidthScale = scale
            }
        })
        bind.seekBarWidth.progress = 100

        bind.seekBarHeight.max = 100
        bind.seekBarHeight.setOnSeekBarChangeListener(object : OnSeekBarChangeSimpleListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val scale = progress / 100.0f * 0.1f
                bind.waveView.waveHeightScale = scale
            }
        })
        bind.seekBarHeight.progress = 35

        bind.seekBarSpeed.max = 4000
        bind.seekBarSpeed.setOnSeekBarChangeListener(object : OnSeekBarChangeSimpleListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val speed = (progress + 300).toLong()
                bind.waveView.speed = speed
            }
        })
        bind.seekBarSpeed.progress = 500

        bind.seekBarColor.max = 100
        bind.seekBarColor.setOnSeekBarChangeListener(object : OnSeekBarChangeSimpleListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                bind.waveView.bgColor = Utils.getRandomColorInt()
            }
        })
    }

}
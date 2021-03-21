package github.leavesc.customview

import android.os.Bundle
import android.widget.SeekBar
import github.leavesc.base.BaseActivity
import github.leavesc.customview.utils.Utils
import github.leavesc.customview.widget.OnSeekBarChangeSimpleListener
import kotlinx.android.synthetic.main.activity_wave_view.*

/**
 * @Author: leavesC
 * @Date: 2020/03/12 01:05
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
class WaveViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wave_view)

        seekBarWidth.max = 100
        seekBarWidth.setOnSeekBarChangeListener(object : OnSeekBarChangeSimpleListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val scale = progress / 100.0f
                waveView.waveWidthScale = scale
            }
        })
        seekBarWidth.progress = 100

        seekBarHeight.max = 100
        seekBarHeight.setOnSeekBarChangeListener(object : OnSeekBarChangeSimpleListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val scale = progress / 100.0f * 0.1f
                waveView.waveHeightScale = scale
            }
        })
        seekBarHeight.progress = 35

        seekBarSpeed.max = 4000
        seekBarSpeed.setOnSeekBarChangeListener(object : OnSeekBarChangeSimpleListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val speed = (progress + 300).toLong()
                waveView.speed = speed
            }
        })
        seekBarSpeed.progress = 500

        seekBarColor.max = 100
        seekBarColor.setOnSeekBarChangeListener(object : OnSeekBarChangeSimpleListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                waveView.bgColor = Utils.getRandomColorInt()
            }
        })
    }

}
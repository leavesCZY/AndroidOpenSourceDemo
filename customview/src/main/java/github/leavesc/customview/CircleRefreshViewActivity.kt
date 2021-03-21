package github.leavesc.customview

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import github.leavesc.base.BaseActivity
import github.leavesc.customview.widget.OnSeekBarChangeSimpleListener
import kotlinx.android.synthetic.main.activity_circle_refresh_view.*

/**
 * @Author: leavesC
 * @Date: 2020/03/12 01:05
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
class CircleRefreshViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_refresh_view)
        seekBarDrag.max = 100
        seekBarDrag.setOnSeekBarChangeListener(object : OnSeekBarChangeSimpleListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                circleRefreshView.drag(progress / 100f)
            }
        })
        seekBarSeed.max = 100
        seekBarSeed.setOnSeekBarChangeListener(object : OnSeekBarChangeSimpleListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                circleRefreshView.speed = (progress * 40).toLong()
            }
        })
        seekBarSeed.progress = (circleRefreshView.speed / 40).toInt()
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnStart -> {
                circleRefreshView.startAnimator()
            }
            R.id.btnStop -> {
                circleRefreshView.stopAnimator()
            }
        }
    }

}
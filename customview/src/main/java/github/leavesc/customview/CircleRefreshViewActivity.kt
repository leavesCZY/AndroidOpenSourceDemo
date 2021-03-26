package github.leavesc.customview

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import github.leavesc.base.BaseActivity
import github.leavesc.customview.databinding.ActivityCircleRefreshViewBinding
import github.leavesc.customview.widget.OnSeekBarChangeSimpleListener

/**
 * @Author: leavesC
 * @Date: 2020/03/12 01:05
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
class CircleRefreshViewActivity : BaseActivity() {

    override val bind by getBind<ActivityCircleRefreshViewBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.seekBarDrag.max = 100
        bind.seekBarDrag.setOnSeekBarChangeListener(object : OnSeekBarChangeSimpleListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                bind.circleRefreshView.drag(progress / 100f)
            }
        })
        bind.seekBarSeed.max = 100
        bind.seekBarSeed.setOnSeekBarChangeListener(object : OnSeekBarChangeSimpleListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                bind.circleRefreshView.speed = (progress * 40).toLong()
            }
        })
        bind.seekBarSeed.progress = (bind.circleRefreshView.speed / 40).toInt()
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnStart -> {
                bind.circleRefreshView.startAnimator()
            }
            R.id.btnStop -> {
                bind.circleRefreshView.stopAnimator()
            }
        }
    }

}
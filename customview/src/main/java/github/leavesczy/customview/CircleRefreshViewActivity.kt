package github.leavesczy.customview

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import github.leavesczy.base.BaseActivity
import github.leavesczy.customview.databinding.ActivityCircleRefreshViewBinding
import github.leavesczy.customview.widget.OnSeekBarChangeSimpleListener

/**
 * @Author: leavesCZY
 * @Date: 2020/03/12 01:05
 * @Github：https://github.com/leavesCZY
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
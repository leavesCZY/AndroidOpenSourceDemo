package github.leavesczy.constraint_layout

import android.content.Context
import android.util.AttributeSet
import android.view.ViewAnimationUtils
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.hypot

/**
 * @Author: leavesCZY
 * @Date: 2020/12/26 23:47
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class CircularRevealHelper @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintHelper(context, attrs, defStyleAttr) {

    override fun updatePostLayout(container: ConstraintLayout) {
        super.updatePostLayout(container)
        val views = getViews(container)
        for (view in views) {
            val anim = ViewAnimationUtils.createCircularReveal(
                view, view.width / 2,
                view.height / 2, 0f,
                hypot((view.height / 2).toDouble(), (view.width / 2).toDouble()).toFloat()
            )
            anim.duration = 3000
            anim.start()
        }
    }

}
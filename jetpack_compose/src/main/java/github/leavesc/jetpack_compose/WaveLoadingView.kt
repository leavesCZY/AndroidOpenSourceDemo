package github.leavesc.jetpack_compose

import android.graphics.Canvas
import android.graphics.Typeface
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp

/**
 * @Author: leavesC
 * @Date: 2021/8/14 20:47
 * @Desc:
 * @Github：https://github.com/leavesC
 */
@Composable
fun WaveLoadingView(
    modifier: Modifier,
    text: String,
    textSizeSp: Int,
    waveColor: Color,
    downTextColor: Color,
) {
    BoxWithConstraints(modifier = modifier) {
        val circleSizeDp = minOf(maxWidth, maxHeight)
        val density = LocalDensity.current.density
        val circleSizePx = circleSizeDp.value * density
        val waveWidth = circleSizePx / 3f
        val waveHeight = circleSizePx / 26f
        val textPaint by remember {
            mutableStateOf(Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                isDither = true
                typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
                textAlign = android.graphics.Paint.Align.CENTER
            })
        }
        val wavePath by remember {
            mutableStateOf(Path())
        }
        val circlePath by remember {
            mutableStateOf(Path().apply {
                addArc(
                    Rect(0f, 0f, circleSizePx, circleSizePx),
                    0f, 360f
                )
            })
        }
        val animateValue by rememberInfiniteTransition().animateFloat(
            initialValue = 0f, targetValue = waveWidth,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
            ),
        )
        Canvas(modifier = modifier.requiredSize(size = circleSizeDp)) {
            drawIntoCanvas {
                textPaint.textSize = textSizeSp.sp.toPx()
                drawText(
                    canvas = it.nativeCanvas,
                    size = circleSizePx,
                    textPaint = textPaint,
                    textColor = waveColor.toArgb(),
                    text = text
                )
            }
            wavePath.reset()
            wavePath.moveTo(-waveWidth + animateValue, circleSizePx / 2.2f)
            var i = -waveWidth
            while (i < circleSizePx + waveWidth) {
                wavePath.relativeQuadraticBezierTo(waveWidth / 4f, -waveHeight, waveWidth / 2f, 0f)
                wavePath.relativeQuadraticBezierTo(waveWidth / 4f, waveHeight, waveWidth / 2f, 0f)
                i += waveWidth
            }
            wavePath.lineTo(circleSizePx, circleSizePx)
            wavePath.lineTo(0f, circleSizePx)
            wavePath.close()

            val resultPath = Path.combine(
                path1 = circlePath,
                path2 = wavePath,
                operation = PathOperation.Intersect
            )
            drawPath(path = resultPath, color = waveColor)

            clipPath(path = resultPath, clipOp = ClipOp.Intersect) {
                drawIntoCanvas {
                    drawText(
                        canvas = it.nativeCanvas,
                        size = circleSizePx,
                        textPaint = textPaint,
                        textColor = downTextColor.toArgb(),
                        text = text
                    )
                }
            }
        }
    }
}

private fun drawText(
    canvas: Canvas,
    size: Float,
    textPaint: android.graphics.Paint,
    textColor: Int,
    text: String
) {
    textPaint.color = textColor
    val fontMetrics = textPaint.fontMetrics
    val top = fontMetrics.top
    val bottom = fontMetrics.bottom
    val centerX = size / 2f
    val centerY = size / 2f - top / 2f - bottom / 2f
    canvas.drawText(text, centerX, centerY, textPaint)
}
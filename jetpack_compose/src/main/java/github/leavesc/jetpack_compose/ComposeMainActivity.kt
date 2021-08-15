package github.leavesc.jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import github.leavesc.jetpack_compose.ui.theme.AndroidOpenSourceDemoTheme

/**
 * @Author: leavesC
 * @Date: 2021/8/14 20:48
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class ComposeMainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidOpenSourceDemoTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(state = ScrollState(0)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        WaveLoadingView(
                            modifier = Modifier.requiredSize(size = 160.dp),
                            text = "开",
                            textSize = 110.sp,
                            waveColor = colorOf("#FFF54183"),
                            downTextColor = Color.White
                        )
                        WaveLoadingView(
                            modifier = Modifier.requiredSize(size = 200.dp),
                            text = "心",
                            textSize = 150.sp,
                            waveColor = colorOf("#FFBB86FC"),
                            downTextColor = Color.White
                        )
                        WaveLoadingView(
                            modifier = Modifier.requiredSize(size = 240.dp),
                            text = "最",
                            textSize = 170.sp,
                            waveColor = colorOf("#FF50A4F7"),
                            downTextColor = Color.White
                        )
                        WaveLoadingView(
                            modifier = Modifier.fillMaxSize(),
                            text = "重",
                            textSize = 240.sp,
                            waveColor = colorOf("#FF6200EE"),
                            downTextColor = Color.White
                        )
                        WaveLoadingView(
                            modifier = Modifier.wrapContentSize(),
                            text = "要",
                            textSize = 260.sp,
                            waveColor = colorOf("#FF009688"),
                            downTextColor = Color.White
                        )
                    }
                }
            }
        }
    }

    private fun colorOf(colorString: String): Color {
        return Color(android.graphics.Color.parseColor(colorString))
    }

}
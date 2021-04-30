package github.leavesc.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import github.leavesc.compose.ui.theme.AndroidOpenSourceDemoTheme

/**
 * @Author: leavesC
 * @Date: 2021/4/30 13:08
 * @Desc:
 * @Github：https://github.com/leavesC
 */
class ComposeMainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidOpenSourceDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = (Modifier
            .alpha(20f)
            .padding(20.dp))
    ) {
        Text(text = "Hello $name!")
        Text(text = "Hello $name!")
        Text(
            text = "Hello $name!", color = Color.Blue,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidOpenSourceDemoTheme {
        Greeting("Android")
    }
}
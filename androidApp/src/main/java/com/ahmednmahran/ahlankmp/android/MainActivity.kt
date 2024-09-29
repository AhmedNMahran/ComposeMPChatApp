package com.ahmednmahran.ahlankmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahmednmahran.ahlankmp.welcome.data.model.Post
import com.ahmednmahran.ahlankmp.welcome.view.PostsScreen
import com.ahmednmahran.ahlankmp.welcome.view.WelcomeScreen
import com.ahmednmahran.ahlankmp.welcome.viewmodel.WelcomeScreenViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = WelcomeScreenViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WelcomeScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}

@Preview
@Composable
fun PostsPreview() {
    PostsScreen(
        listOf(
        Post("body1", 1, "title1", 1),
        Post("sdfsdfsdfsdf", 1, "tit5555le1", 1),
        Post("sdfsdfs", 1, "titl2222e1", 1),
        Post("fsdfsdfdsdffffffffffffff", 1, "title1", 1),
        Post("body1", 1, "titl2322e1", 1),)
    )
}

package com.ahmednmahran.composempchat.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahmednmahran.composempchat.App

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                App()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Test(){
    Text(text = "fsldkfjlskdfjlskdfjlskdfjlskdjflskdjf", modifier = Modifier.wrapContentHeight())
}

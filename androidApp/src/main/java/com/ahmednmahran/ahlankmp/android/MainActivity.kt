package com.ahmednmahran.ahlankmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahmednmahran.ahlankmp.App
import com.ahmednmahran.ahlankmp.login.view.LoginScreenContent

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


@Composable
fun GreetingView(text: String) {
    Text(text = text)
}


//@Preview
//@Composable
//fun DefaultPreview() {
//    MyApplicationTheme {
//        GreetingView("Hello, Android!")
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewChatScreenContent() {
//    ChatScreenContent(
//        chatMessages = listOf(
//            ChatMessage(sender = "Ahmed", body = "Hello!"),
//            ChatMessage(sender = "Muhammad", body = "How are you?")
//        ),
//        alertMessage = "",
//        user = User(username = "Ali", password = "password"),
//        onSendMessage = {}
//    )
//}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview(){
    LoginScreenContent()
}

package com.ahmednmahran.ahlankmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahmednmahran.ahlankmp.chat.data.model.ChatMessage
import com.ahmednmahran.ahlankmp.chat.data.model.User
import com.ahmednmahran.ahlankmp.chat.data.repository.ChatRepository
import com.ahmednmahran.ahlankmp.chat.view.ChatScreen
import com.ahmednmahran.ahlankmp.chat.view.ChatScreenContent
import com.ahmednmahran.ahlankmp.chat.viewmodel.ChatViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = ChatViewModel(ChatRepository(User("Ahmed", ""), "10.0.2.2"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var loggedIn by remember { mutableStateOf(false) }
                    if (loggedIn)
                        ChatScreen(viewModel)
                    else
                        LoginScreen { logged ->
                            loggedIn = logged
                        }
                }
            }
        }
    }
}


@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Composable
fun LoginScreen(onLogin: (Boolean) -> Unit) {
    Button(onClick = { onLogin(true) }) {
        Text(text = "Login")
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewChatScreenContent() {
    ChatScreenContent(
        chatMessages = listOf(
            ChatMessage(sender = "Ahmed", body = "Hello!"),
            ChatMessage(sender = "Muhammad", body = "How are you?")
        ),
        alertMessage = "",
        user = User(username = "Ali", password = "password"),
        onSendMessage = {}
    )
}

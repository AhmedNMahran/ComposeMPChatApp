package com.ahmednmahran.ahlankmp.chat.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ahmednmahran.ahlankmp.chat.viewmodel.ChatViewModel
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import cafe.adriel.voyager.core.screen.Screen
import com.ahmednmahran.ahlankmp.chat.data.model.ChatMessage
import com.ahmednmahran.ahlankmp.chat.data.model.User
import com.ahmednmahran.ahlankmp.chat.data.repository.ChatRepository


data class DirectChatScreen(val currentUser: User, val chatUser: User) : Screen {
    @Composable
    override fun Content() {
        val viewModel by remember { mutableStateOf(ChatViewModel(ChatRepository(chatUser))) }
        DirectChatScreen(viewModel, chatUser, currentUser)
    }
}

@Composable
fun DirectChatScreen(viewModel: ChatViewModel, toUser: User, currentUser: User) {
    val chatMessages by viewModel.chatMessages.collectAsState()
    // Wrap the ChatScreenContent and pass the necessary values
    DirectChatScreenContent(
        chatMessages = chatMessages,
        user = currentUser,
        toUser = toUser,
        onSendMessage = { message ->
            viewModel.sendMessage(message, toUser.username)
        }
    )
}

@Composable
fun DirectChatScreenContent(
    chatMessages: List<ChatMessage>,
    user: User,
    toUser: User,
    onSendMessage: (String) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {


        // Display the current user
        Text(text = "you are currently chatting with ${toUser.username}")

        // Display chat messages
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(chatMessages.size) { index ->
                val message = chatMessages[index]
                ChatItem(message, if(user.username == message.sender) user else toUser, user)
            }
        }

        // Text input for sending new messages
        var message by remember { mutableStateOf("") }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                onSendMessage(message)
                message = "" // Clear the message after sending
            }) {
                Text(text = "Send")
            }
        }
    }

}

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import com.ahmednmahran.ahlankmp.chat.data.model.ChatMessage
import com.ahmednmahran.ahlankmp.chat.data.model.User

@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    val chatMessages by viewModel.chatMessages.collectAsState()
    val alert by viewModel.alert.collectAsState()
    val user by viewModel.user.collectAsState()

    // Wrap the ChatScreenContent and pass the necessary values
    ChatScreenContent(
        chatMessages = chatMessages,
        alertMessage = alert,
        user = user,
        onSendMessage = { message ->
            viewModel.sendMessage(message)
        }
    )
}

@Composable
fun ChatScreenContent(
    chatMessages: List<ChatMessage>,
    alertMessage: String,
    user: User,
    onSendMessage: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Display alert message if any
        if (alertMessage.isNotEmpty()) {
            Text(text = "Alert: $alertMessage", color = MaterialTheme.colorScheme.error)
        }

        // Display the current user
        Text(text = "User: ${user.username}")

        // Display chat messages
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(chatMessages.size) { index ->
                val message = chatMessages[index]
                Text(text = "${message.sender}: ${message.body}")
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
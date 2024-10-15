package com.ahmednmahran.ahlankmp.chat.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ahmednmahran.ahlankmp.chat.viewmodel.ChatViewModel
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import coil3.compose.AsyncImage
import com.ahmednmahran.ahlankmp.chat.data.model.ChatMessage
import com.ahmednmahran.ahlankmp.chat.data.model.User
import com.ahmednmahran.ahlankmp.chat.data.repository.ChatRepository
import com.ahmednmahran.ahlankmp.connections.view.ConnectionsPage


data class ChatScreen(val chatUser: User) : Screen {
    @Composable
    override fun Content() {
        val viewModel by remember { mutableStateOf(ChatViewModel(ChatRepository(chatUser))) }
        ChatScreen(viewModel)
    }
}

@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    val chatMessages by viewModel.chatMessages.collectAsState()
    val alert by viewModel.alert.collectAsState()
    val user by viewModel.user.collectAsState()
    val connections by viewModel.connections.collectAsState()
    viewModel.getUsers()
    // Wrap the ChatScreenContent and pass the necessary values
    ChatScreenContent(
        chatMessages = chatMessages,
        alertMessage = alert,
        user = user,
        connections = connections,
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
    connections: List<User>,
    onSendMessage: (String) -> Unit
) {
    val navigator = LocalNavigator.current

    Column(modifier = Modifier.fillMaxSize()) {
        // Display alert message if any
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(connections) {
                AsyncImage(
                    modifier = Modifier.size(48.dp).clip(CircleShape)
                        .clickable {
                            navigator?.push(DirectChatScreen(user, it))
                        },

                    model = it.profileImageUrl, contentDescription = "connection profile image"
                )
            }
        }
        Button(onClick = {
            navigator?.push(ConnectionsPage(connections))
        }) {
            Text(text = "Direct Chats")
        }

        // Display the current user
        Text(text = "User: ${user.username}")

        // Display chat messages
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(chatMessages.size) { index ->
                val message = chatMessages[index]
                ChatItem(message, user, user)
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

@Composable
fun ChatItem(message: ChatMessage, sender: User, currentUser: User) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.sender == currentUser.username) Arrangement.End else Arrangement.Start
    ) {
        if (message.sender == currentUser.username)
            Spacer(modifier = Modifier.weight(1f))
        AsyncImage(
            model = sender.profileImageUrl,
            contentDescription = null,
            modifier = Modifier.size(48.dp).clip(CircleShape)
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = message.sender,
                modifier = Modifier.wrapContentWidth(),
                style = MaterialTheme.typography.labelMedium
            )
            Text(text = message.body, modifier = Modifier.wrapContentSize())
        }
        if (message.sender != currentUser.username)
            Spacer(modifier = Modifier.weight(1f))
    }
//    DataStoreFactory.
}

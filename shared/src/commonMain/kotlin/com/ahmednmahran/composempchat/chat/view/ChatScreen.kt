package com.ahmednmahran.composempchat.chat.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ahmednmahran.composempchat.chat.viewmodel.ChatViewModel
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
import com.ahmednmahran.composempchat.chat.data.model.ChatMessage
import com.ahmednmahran.composempchat.chat.data.model.User
import com.ahmednmahran.composempchat.chat.data.repository.ChatRepository
import com.ahmednmahran.composempchat.connections.view.ConnectionsPage


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

    // TODO: create the content that has lzaycolumn of ChatItems and text fields with send button

}

@Composable
fun ChatItem(message: ChatMessage, sender: User, currentUser: User) {
    // TODO: create the chat item for every message
}

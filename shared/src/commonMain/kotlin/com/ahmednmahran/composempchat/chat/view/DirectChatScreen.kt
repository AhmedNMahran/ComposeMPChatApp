package com.ahmednmahran.composempchat.chat.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ahmednmahran.composempchat.chat.viewmodel.ChatViewModel
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import cafe.adriel.voyager.core.screen.Screen
import com.ahmednmahran.composempchat.chat.data.model.ChatMessage
import com.ahmednmahran.composempchat.chat.data.model.User
import com.ahmednmahran.composempchat.chat.data.repository.ChatRepository


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

// TODO: create the direct chat content

}

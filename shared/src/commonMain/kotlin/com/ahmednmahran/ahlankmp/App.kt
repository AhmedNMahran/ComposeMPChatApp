package com.ahmednmahran.ahlankmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ahmednmahran.ahlankmp.chat.data.model.User
import com.ahmednmahran.ahlankmp.chat.data.repository.ChatRepository


import com.ahmednmahran.ahlankmp.chat.view.ChatScreen
import com.ahmednmahran.ahlankmp.chat.viewmodel.ChatViewModel
import com.ahmednmahran.ahlankmp.login.data.repository.LoginRepository
import com.ahmednmahran.ahlankmp.login.view.LoginScreen
import com.ahmednmahran.ahlankmp.login.viewmodel.LoginViewModel


@Composable
fun App() {
    val chatViewModel by remember { mutableStateOf( ChatViewModel(ChatRepository(User("Ahmed", ""), "10.0.2.2"))) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var loggedIn by remember { mutableStateOf(false) }
        if (loggedIn)
            ChatScreen(chatViewModel)
        else
            LoginScreen(LoginViewModel(LoginRepository())){
                loggedIn = true
            }
    }
}
package com.ahmednmahran.composempchat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import coil3.compose.AsyncImage
import com.ahmednmahran.composempchat.chat.view.ChatScreen


import com.ahmednmahran.composempchat.login.view.LoginScreen
import kotlinx.coroutines.delay


@Composable
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Navigator(LoginScreen())
    }
}


class HomeScreen : Screen {
    @Composable
    override fun Content() {
        HomeScreenContent()
    }
}

@Composable
fun HomeScreenContent(){
    val navigator = LocalNavigator.current
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier.size(96.dp).clip(CircleShape),
            contentScale = ContentScale.Fit,
            model = "https://media.istockphoto.com/id/1153425570/vector/quiz-or-exam-online-on-computer-screen-vector-illustration-flat-cartoon-laptop-with.jpg?s=2048x2048&w=is&k=20&c=ajY3h_KOwXNX3dlY1UKBmsiRsej5qZGbkI0xd7e0p74=",
            contentDescription = null,
        )
        Text("Welcome to Chat App", style = MaterialTheme.typography.headlineLarge)
    }
    LaunchedEffect(Unit) {
        delay(2000)
        val user = Database.user
        if (user == null)
            navigator?.push(LoginScreen())
        else
            navigator?.push(ChatScreen(user))
    }
}
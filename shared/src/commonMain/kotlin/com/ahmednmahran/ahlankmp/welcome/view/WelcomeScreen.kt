package com.ahmednmahran.ahlankmp.welcome.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.ahmednmahran.ahlankmp.welcome.data.model.Post
import com.ahmednmahran.ahlankmp.welcome.data.model.User
import com.ahmednmahran.ahlankmp.welcome.viewmodel.WelcomeScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(viewModel: WelcomeScreenViewModel) {
    viewModel.login()
    val user = viewModel.user.collectAsState()
    val scope = rememberCoroutineScope()
    var posts: List<Post> by remember { mutableStateOf(emptyList()) }
    var greeting: String by remember { mutableStateOf("Loading...") }
    LaunchedEffect(true) {
        scope.launch {
            posts = try {
                viewModel.greeting()
            } catch (e: Exception) {
                println("Error: ${e.message}")
                greeting = e.message ?:"no message"
                emptyList()
            }
        }
    }
    if(posts.isNotEmpty()){
        PostsScreen(posts)
    }
//    greeting = if(posts.isEmpty()) "empty" else posts.first().body
//
//    WelcomeScreenContent(user.value, greeting)


}

@Composable
private fun WelcomeScreenContent(user: User, greeting: String) {
    val name: String = user.username
    Column {
        Text(text = greeting)
    }
}
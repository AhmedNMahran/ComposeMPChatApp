package com.ahmednmahran.ahlankmp.connections.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImage
import com.ahmednmahran.ahlankmp.chat.data.model.User

data class ConnectionsPage(private val users: List<User>) : Screen {
    @Composable
    override fun Content() {
        val usersState by remember { mutableStateOf(users) }

        Connections(usersState)
    }

    @Composable
    private fun Connections(usersState: List<User>) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(usersState) { user ->
                UserItem(user)
            }
        }
    }

    @Composable
    private fun UserItem(user: User) {
        Row(modifier = Modifier.wrapContentHeight().fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AsyncImage(
                model = user.profileImageUrl, modifier = Modifier.size(48.dp).clip(
                    CircleShape
                ), contentDescription = "user profile image"
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(user.username, style = MaterialTheme.typography.titleLarge)
                Text(dummyMessages.random(), style = MaterialTheme.typography.bodySmall)
            }
        }
    }

    private val dummyMessages =
        listOf("how are you today?", "excited to meet you \uD83E\uDD29", " will call you later,...")
}
package com.ahmednmahran.ahlankmp.connections.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import com.ahmednmahran.ahlankmp.chat.data.model.User

data class ConnectionsPage(private val users: List<User>): Screen {
    @Composable
    override fun Content() {
        val usersState by remember { mutableStateOf(users) }

        Connections(usersState)
    }

    @Composable
    private fun Connections(usersState: List<User>) {
        LazyColumn {
            items(usersState.size) { index ->
                val user = usersState[index]
                Text(text = user.username)
            }
        }
    }

}
package com.ahmednmahran.ahlankmp.welcome.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ahmednmahran.ahlankmp.welcome.data.model.Post

@Composable
fun PostsScreen(posts: List<Post>) {
    LazyColumn {
        items(posts) {
            PostItem(it)
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).height(200.dp)) {
        Column {
            Text(text = post.title, style = MaterialTheme.typography.titleMedium)
            Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
            Text(text = post.userId.toString(), color = Color.DarkGray)
        }

    }
}
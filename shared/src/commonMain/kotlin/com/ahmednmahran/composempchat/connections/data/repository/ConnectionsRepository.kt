package com.ahmednmahran.composempchat.connections.data.repository

import com.ahmednmahran.composempchat.Client
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets

class ConnectionsRepository {
    private val client: HttpClient by lazy {
        Client.getInstance().config {
            install(WebSockets)
        }
    }
}
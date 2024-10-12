package com.ahmednmahran.ahlankmp

import com.ahmednmahran.ahlankmp.chat.data.model.User
import io.ktor.client.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.json.*

object Client {
    private var client: HttpClient? = null

    fun initialize(chatUser: User) {
        if (client == null) {
            client = HttpClient {
                install(ContentNegotiation) {
                    json()
                }
                install(Auth) {
                    basic {
                        credentials {
                            BasicAuthCredentials(
                                username = chatUser.username,
                                password = chatUser.password
                            )
                        }
                        realm = "Access to the '/' path"
                    }
                }
                install(WebSockets)
            }
        }
    }

    fun getInstance(): HttpClient {
        return client ?: throw IllegalStateException("HttpClient is not initialized. Call initialize() first.")
    }

    fun clear() {
        client?.close()
        client = null
    }
}
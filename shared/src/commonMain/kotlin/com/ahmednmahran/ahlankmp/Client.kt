package com.ahmednmahran.ahlankmp

import com.ahmednmahran.ahlankmp.chat.data.model.User
import io.ktor.client.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.json.*

fun client(chatUser: User): HttpClient =
    HttpClient {
        install(ContentNegotiation){
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
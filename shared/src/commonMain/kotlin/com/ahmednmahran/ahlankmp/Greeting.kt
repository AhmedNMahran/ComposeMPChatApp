package com.ahmednmahran.ahlankmp


import io.ktor.client.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.*

import io.ktor.serialization.kotlinx.json.json

class Greeting {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation){
            json()
        }
        install(WebSockets)

    }

}
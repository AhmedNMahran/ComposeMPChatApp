package com.ahmednmahran.ahlankmp

import com.ahmednmahran.ahlankmp.welcome.data.model.Post
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json

class Greeting {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation){
            json()
        }
    }

    suspend fun greeting(): ArrayList<Post> {
        val response = client.get("https://jsonplaceholder.typicode.com/posts/")
        return response.body()
    }
}
package com.ahmednmahran.ahlankmp.login.data.repository

import com.ahmednmahran.ahlankmp.Client
import com.ahmednmahran.ahlankmp.baseHost
import com.ahmednmahran.ahlankmp.chat.data.model.User
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json


class LoginRepository() {

    suspend fun login(username: String, password: String): Result<User> {

        return try {
            Client.let {

                it.initialize(User(username, password))

                val response = it.getInstance().post("login") {
                    host = baseHost
                    port = 8080
                }

                if (response.status.isSuccess()) {
                    val user: User = Json.decodeFromString(response.bodyAsText()) // Parse user data from response
                    Result.success(user)  // Return the authenticated user
                } else {
                    Result.failure(Exception("Login failed with status: ${response.status}"))
                }

            }
        } catch (e: Exception) {
            Result.failure(e)  // Handle login error
        }
    }
}
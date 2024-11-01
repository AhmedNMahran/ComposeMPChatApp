package com.ahmednmahran.composempchat.login.data.repository

import com.ahmednmahran.composempchat.Client
import com.ahmednmahran.composempchat.Client.loggedUser
import com.ahmednmahran.composempchat.baseHost
import com.ahmednmahran.composempchat.chat.data.model.User
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json


class LoginRepository() {
    lateinit var user: User
    suspend fun login(username: String, password: String): Result<User> {

        return try {
            Client.let {
                it.initialize(User(username, password))

                val response = it.getInstance().post("login") {
                    basicAuth(username, password)
                    host = baseHost
                    port = 8080
                }

                if (response.status.isSuccess()) {
                    user = Json.decodeFromString(response.bodyAsText()) // Parse user data from response
                    loggedUser = user
                    Result.success(loggedUser)  // Return the authenticated user
                } else {
                    Result.failure(Exception("Login failed with status: ${response.status}"))
                }

            }
        } catch (e: Exception) {
            Result.failure(e)  // Handle login error
        }
    }

    fun isLoggedIn(): Boolean {
        return ::user.isInitialized
    }
}
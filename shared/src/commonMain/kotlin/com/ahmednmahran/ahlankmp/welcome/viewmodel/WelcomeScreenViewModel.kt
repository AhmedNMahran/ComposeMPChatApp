package com.ahmednmahran.ahlankmp.welcome.viewmodel

import com.ahmednmahran.ahlankmp.Greeting
import com.ahmednmahran.ahlankmp.welcome.data.datasource.remote.WelcomeRemoteDataSource
import com.ahmednmahran.ahlankmp.welcome.data.model.User
import com.ahmednmahran.ahlankmp.welcome.data.repository.WelcomeRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WelcomeScreenViewModel(
    private val repository: WelcomeRepository =
        WelcomeRepository(WelcomeRemoteDataSource())
) {

    val valueChanged = false
    private val viewModelScope = GlobalScope
    // live data
    // observable

    private val _user = MutableStateFlow(User("initial", "pass"))
    val user: StateFlow<User> = _user

    fun login() = viewModelScope.launch {
        repository.getUserData().collect { result ->
            _user.update {
                result
            }
        }
    }

    suspend fun greeting() = Greeting().greeting()


//    val client = HttpClient()
//    suspend fun getPostById(postId: Int): Post {
//        val url = "https://jsonplaceholder.typicode.com/posts/$postId"
//        return client.get(url)
//    }

    // Usage in a coroutine
//    suspend fun exampleGetRequest() {
//        try {
//            val post = getPostById(1)
//            println("Post Title: ${post.title}")
//        } catch (e: Exception) {
//            println("Failed: ${e.message}")
//        }
//    }


    // coroutines
    // flows
}
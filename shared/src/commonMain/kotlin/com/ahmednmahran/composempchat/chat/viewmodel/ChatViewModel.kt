package com.ahmednmahran.composempchat.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmednmahran.composempchat.chat.data.model.ChatMessage
import com.ahmednmahran.composempchat.chat.data.model.User
import com.ahmednmahran.composempchat.chat.data.repository.ChatRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel() {

    // Expose the chat messages from the repository
    val chatMessages: StateFlow<List<ChatMessage>> = chatRepository.chatMessages

    // Expose alert messages from the repository
    val alert: StateFlow<String> = chatRepository.alert

    // Expose user state from the repository
    val user: StateFlow<User> = chatRepository.user
    val connections: StateFlow<List<User>> = chatRepository.users

    init {
        connect()
    }

    // Function to send messages
    fun sendMessage(message: String, to: String? = null) {
        viewModelScope.launch {
            chatRepository.send(message, to)
        }
    }

    fun getUsers(){
        viewModelScope.launch {
            chatRepository.getUsers()
        }
    }

    // Function to connect manually (if needed)
    fun connect() {
        viewModelScope.launch {
            chatRepository.connect()
        }
    }

    // Clean up any resources when ViewModel is cleared
    override fun onCleared() {
        super.onCleared()
        chatRepository.disconnect() // Close the chat
    }
}
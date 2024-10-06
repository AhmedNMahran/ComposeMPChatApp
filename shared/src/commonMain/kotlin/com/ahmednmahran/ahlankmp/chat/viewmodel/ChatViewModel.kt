package com.ahmednmahran.ahlankmp.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmednmahran.ahlankmp.chat.data.model.ChatMessage
import com.ahmednmahran.ahlankmp.chat.data.model.User
import com.ahmednmahran.ahlankmp.chat.data.repository.ChatRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel() {

    // Expose the chat messages from the repository
    val chatMessages: StateFlow<List<ChatMessage>> = chatRepository.chatMessages

    // Expose alert messages from the repository
    val alert: StateFlow<String> = chatRepository.alert

    // Expose user state from the repository
    val user: StateFlow<User> = chatRepository.user

    init {

        connect()
        viewModelScope.launch {

        }
    }

    // Function to send messages
    fun sendMessage(message: String) {
        viewModelScope.launch {
            chatRepository.send(message)
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
        // Optionally handle any cleanup here if needed
    }
}
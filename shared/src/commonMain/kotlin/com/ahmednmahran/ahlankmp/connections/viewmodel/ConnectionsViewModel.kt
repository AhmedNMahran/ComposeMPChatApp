package com.ahmednmahran.ahlankmp.connections.viewmodel

import androidx.lifecycle.ViewModel
import com.ahmednmahran.ahlankmp.chat.data.model.User
import com.ahmednmahran.ahlankmp.connections.data.repository.ConnectionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ConnectionsViewModel(
    private val repository: ConnectionsRepository
): ViewModel() {

    private val _connections = MutableStateFlow<List<User>>(emptyList())
    val connections: StateFlow<List<User>> = _connections
}
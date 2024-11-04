package com.ahmednmahran.composempchat.chat.data.repository


import com.ahmednmahran.composempchat.Client
import com.ahmednmahran.composempchat.baseHost
import com.ahmednmahran.composempchat.chat.data.model.ChatMessage
import com.ahmednmahran.composempchat.chat.data.model.User
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.get
import io.ktor.client.request.host
import io.ktor.client.request.port
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ChatRepository(private val chatUser: User) {
    private val client: HttpClient by lazy {
        Client.getInstance().config {
            install(WebSockets)
        }
    }
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages

    private var _alert = MutableStateFlow("")
    val alert: StateFlow<String> = _alert

    private var _user = MutableStateFlow(User("", "", ""))
    val user = _user
    private var _users = MutableStateFlow(emptyList<User>())
    val users: StateFlow<List<User>> = _users

    private val _job by lazy {
        CoroutineScope(Dispatchers.Default).launch {
            connect()
            startChat()
        }

    }
    private var _session: WebSocketSession? = null

    init {
        if (!_job.isActive)
            _job.start()
    }

    suspend fun connect() {
        // todo init the _session with websocket and emit the user
    }

    private suspend fun startChat() {
        // TODO: call receive() then start chat and handle try/catch
    }

    suspend fun send(message: String, to: String?) {
        // TODO: create [ChatMessage] and send to frame using Frame.Text after encoding json
    }


    private suspend fun receive() {
        // TODO: keep receiving while true, call [extractChatMessage]
    }

    private suspend fun extractChatMessage(it: String) {
        // TODO: extract the chat message and emit either _chatMessage or _alert
    }

    suspend fun getUsers() {
        // TODO: get connected-users and emit them
    }

    fun disconnect() {
        _job.cancel()
    }


}
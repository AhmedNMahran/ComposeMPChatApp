package com.ahmednmahran.ahlankmp.chat.data.repository


import com.ahmednmahran.ahlankmp.chat.data.model.ChatMessage
import com.ahmednmahran.ahlankmp.chat.data.model.User
import io.ktor.client.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ChatRepository(private val chatUser: User, private val host: String = "127.0.0.1"
) {
    private val client: HttpClient by lazy {
        HttpClient {
            install(Auth){
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
    }
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages

    private var _alert = MutableStateFlow("")
    val alert: StateFlow<String> = _alert

    private var _user = MutableStateFlow(User("","",""))
    val user = _user

    private val _job by lazy {
        CoroutineScope(Dispatchers.Default).launch {
            val response: HttpResponse = client.post("login") {
                host = this@ChatRepository.host
                port = 8080
            }
            println(response.bodyAsText())
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
        _session = client.webSocketSession(
            method = HttpMethod.Get,
            host = host,
            port = 8080,
            path = "/chat"
        )
        _user.emit(chatUser)
        println(chatUser.toString())
    }

    private suspend fun startChat() {
        try {
            receive()
        } catch (e: Exception) {
            if (e is ClosedReceiveChannelException) {
                _alert.emit("Disconnected. ${e.message}.")
            } else if (e is WebSocketException) {
                _alert.emit("Unable to connect.")
            }
            withTimeout(5000) {
                CoroutineScope(Dispatchers.Default).launch { startChat() }
            }
        }
    }

    suspend fun send(message: String) {
        val messageObject = ChatMessage(body = message, sender = _user.value.username)
        _session?.send(Frame.Text(Json.encodeToString(messageObject)))
    }


    private suspend fun receive() {
        while (true) {
            val frame = _session?.incoming?.receive()
            if (frame is Frame.Text) {
                extractChatMessage(frame.readText())
            }
        }
    }

    private suspend fun extractChatMessage(it: String) {
        println("extract: $it")
        try{
            val newMessage = Json.decodeFromString<ChatMessage>(it)
            _chatMessages.emit(_chatMessages.value + newMessage)
            _alert.emit("")
        }catch (th: Throwable){
            _alert.emit(th.message ?: "there was an error.")
        }
    }


}
package com.ahmednmahran.ahlankmp.chat.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(val body: String="", val sender : String="",)

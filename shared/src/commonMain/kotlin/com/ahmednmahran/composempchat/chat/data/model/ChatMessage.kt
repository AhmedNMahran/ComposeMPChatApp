package com.ahmednmahran.composempchat.chat.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(val body: String="", val sender : String="", val to: String? = null)

package com.ahmednmahran.ahlankmp.chat.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(val username: String, val password: String, val profileImageUrl: String? = null)

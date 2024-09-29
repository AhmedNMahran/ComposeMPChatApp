package com.ahmednmahran.ahlankmp.welcome.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    @SerialName("body")
    val body: String,
    @SerialName("id")
    private val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("userId")
    val userId: Int
)
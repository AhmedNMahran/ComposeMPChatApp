package com.ahmednmahran.ahlankmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
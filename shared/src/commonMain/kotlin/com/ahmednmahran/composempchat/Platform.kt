package com.ahmednmahran.composempchat

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect val baseHost: String
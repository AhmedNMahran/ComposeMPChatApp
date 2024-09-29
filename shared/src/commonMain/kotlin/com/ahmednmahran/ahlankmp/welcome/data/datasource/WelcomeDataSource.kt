package com.ahmednmahran.ahlankmp.welcome.data.datasource

import com.ahmednmahran.ahlankmp.welcome.data.model.User

interface WelcomeDataSource {
    suspend fun getUserData(): User
}
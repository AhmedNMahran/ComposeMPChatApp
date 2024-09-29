package com.ahmednmahran.ahlankmp.welcome.data.datasource.local

import com.ahmednmahran.ahlankmp.welcome.data.datasource.WelcomeDataSource
import com.ahmednmahran.ahlankmp.welcome.data.model.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class WelcomeLocalDataSource: WelcomeDataSource {
    override suspend fun getUserData(): User {
        delay(2_000)
        return User("Ahmed Nabil Local", "12345")
    }
}
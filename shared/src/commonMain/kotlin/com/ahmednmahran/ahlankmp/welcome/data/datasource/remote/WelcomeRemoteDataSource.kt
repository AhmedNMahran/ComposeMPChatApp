package com.ahmednmahran.ahlankmp.welcome.data.datasource.remote

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.ahmednmahran.ahlankmp.welcome.data.datasource.WelcomeDataSource
import com.ahmednmahran.ahlankmp.welcome.data.model.User
import com.ahmednmahran.ahlankmp.welcome.viewmodel.WelcomeScreenViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class WelcomeRemoteDataSource : WelcomeDataSource {

    override suspend fun getUserData(): User {
        delay(2_000)
        return User("Ahmed Nabil Remote", "12345")
    }
}

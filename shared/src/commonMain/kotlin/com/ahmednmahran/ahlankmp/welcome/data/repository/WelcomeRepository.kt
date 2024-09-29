package com.ahmednmahran.ahlankmp.welcome.data.repository

import com.ahmednmahran.ahlankmp.welcome.data.datasource.WelcomeDataSource
import com.ahmednmahran.ahlankmp.welcome.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WelcomeRepository (private val dataSource: WelcomeDataSource) {

    fun getUserData() = flow {
        for (i in 1..10) {
            kotlinx.coroutines.delay(1000)
            emit(dataSource.getUserData().copy(username = "Ahmed Nabil $i"))
        }
    }

}



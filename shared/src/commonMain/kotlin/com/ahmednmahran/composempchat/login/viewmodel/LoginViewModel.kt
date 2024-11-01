package com.ahmednmahran.composempchat.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmednmahran.composempchat.chat.data.model.User
import com.ahmednmahran.composempchat.login.data.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _loginState: MutableStateFlow<LoginUIState> = MutableStateFlow(LoginUIState.Idle)
    val loginState: StateFlow<LoginUIState> = _loginState

    sealed class LoginUIState {
        data object Idle : LoginUIState()
        data object Loading : LoginUIState()
        data class Success(val user: User) : LoginUIState()
        data class Error(val message: String) : LoginUIState()
    }

    init {
        repository.run {
            if(isLoggedIn())
                this@LoginViewModel.login(user.username, user.password)
        }
    }

    fun login(username: String, password: String) = viewModelScope.launch {
        val result = repository.login(username, password)
        _loginState.update {
            when {
                result.isSuccess -> {
                    result.getOrNull()?.let { user ->
                        LoginUIState.Success(user)
                    } ?: error(result)
                }

                else -> {
                    error(result)
                }
            }

        }
    }

    private fun error(result: Result<User>) =
        LoginUIState.Error(result.exceptionOrNull()?.message ?: "Unknown error")

}
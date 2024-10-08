package com.ahmednmahran.ahlankmp.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmednmahran.ahlankmp.chat.data.model.User
import com.ahmednmahran.ahlankmp.login.data.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository): ViewModel() {

    private val _loginState: StateFlow<LoginUIState> = MutableStateFlow(LoginUIState.Loading)
    val loginState: StateFlow<LoginUIState> = _loginState

    sealed class LoginUIState {
        object Loading : LoginUIState()
        data class Success(val user: User,) : LoginUIState()
        object Error : LoginUIState()
    }

    fun login() = viewModelScope.launch {
        repository.login()
        // TODO (1): make login() return flow
        // TODO (2): collect the flow
        // TODO (3): update the state based on the flow value
    }

}
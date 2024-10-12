package com.ahmednmahran.ahlankmp.login.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType.Companion.Password
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.ahmednmahran.ahlankmp.chat.view.ChatScreen
import com.ahmednmahran.ahlankmp.login.data.repository.LoginRepository
import com.ahmednmahran.ahlankmp.login.viewmodel.LoginViewModel
import com.ahmednmahran.ahlankmp.view.Loading
import org.jetbrains.compose.resources.InternalResourceApi


class LoginScreen : Screen {
    @Composable
    override fun Content() {
        LoginScreen(LoginViewModel(LoginRepository()))
    }

}

@Composable
private fun LoginScreen(viewModel: LoginViewModel) {
    val loginState = viewModel.loginState.collectAsState()
    when (val uiState = loginState.value) {
        is LoginViewModel.LoginUIState.Idle -> {
            LoginScreenContent(onLoginClick = viewModel::login)
        }
        is LoginViewModel.LoginUIState.Error -> {
            LoginScreenContent(uiState.message, viewModel::login)
        }

        LoginViewModel.LoginUIState.Loading -> {
            Loading()
        }

        is LoginViewModel.LoginUIState.Success -> {
            LocalNavigator.current?.push(ChatScreen(uiState.user))
        }
    }

}




@OptIn(InternalResourceApi::class)
@Composable
private fun LoginScreenContent(message: String = "", onLoginClick: (String, String) -> Unit = { _, _ -> }) {

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

        Card(modifier = Modifier.wrapContentSize().padding(16.dp), shape = CutCornerShape(8.dp)) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                if (message.isNotBlank()) {
                    Text(text = message, color = MaterialTheme.colorScheme.error)
                }
                Text("Welcome To Chat App")
                var username: String by remember { mutableStateOf("") }
                var password: String by remember { mutableStateOf("") }
                TextField(
                    value = username,
                    label = { Text("Username") },
                    onValueChange = {
                    username = it
                })
                TextField(
                    value = password,
                    label = { Text("Password") },
                    onValueChange = {
                        password = it
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = Password),
                )

                Button(onClick = { onLoginClick(username, password) }) {
                    Text("Login")
                }

            }
        }
    }


}
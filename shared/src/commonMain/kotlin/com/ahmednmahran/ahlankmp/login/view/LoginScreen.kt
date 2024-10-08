package com.ahmednmahran.ahlankmp.login.view

import ahlankmp.shared.generated.resources.Res
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType.Companion.Password
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.ahmednmahran.ahlankmp.login.viewmodel.LoginViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.painterResource


@Composable
fun LoginScreen(viewModel: LoginViewModel, onLoggedIn: () -> Unit) {
    LoginScreenContent(){
        viewModel.login()

        // TODO (4): collect the login state using collectAsState()
        // if success, call onLoggedIn
        // else show error
        onLoggedIn()
    }

}


@Composable
fun Error(){
// TODO (5): show simple error
}

@OptIn(InternalResourceApi::class)
@Composable
fun LoginScreenContent(onLoginClick: () -> Unit = {}) {

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

        Card(modifier = Modifier.wrapContentSize().padding(16.dp), shape = CutCornerShape(8.dp)) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Text("Welcome To Chat App")
                var username: String by remember { mutableStateOf("username") }
                var password: String by remember { mutableStateOf("password") }
                TextField(username, {
                    username = it
                })
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = Password),
                )

                Button(onClick = onLoginClick) {
                    Text("Login")
                }

            }
        }
    }



}
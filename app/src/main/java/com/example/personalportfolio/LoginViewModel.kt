package com.example.personalportfolio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf("")
    var loginSuccess by mutableStateOf(false)
    var toastMessage by mutableStateOf<String?>(null)

    fun onUsernameChange(newValue: String) {
        username = newValue
    }

    fun onPasswordChange(newValue: String) {
        password = newValue
    }

    fun login() {
        when {
            username.isBlank() || password.isBlank() -> {
                errorMessage = "Please fill in all fields"
                toastMessage = "Login failed: Missing fields"
            }
            username == "admin" && password == "password" -> {
                errorMessage = ""
                loginSuccess = true
                toastMessage = "Welcome back, $username!"
            }
            else -> {
                errorMessage = "Invalid credentials"
                toastMessage = "Login failed: Wrong username or password"
            }
        }
    }

    fun clearToastMessage() {
        toastMessage = null
    }
}

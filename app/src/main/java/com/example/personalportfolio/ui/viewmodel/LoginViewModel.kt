package com.example.personalportfolio.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.personalportfolio.data.repository.UserRepository
import com.example.personalportfolio.ui.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(private val repository: UserRepository = UserRepository()) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUsernameChange(newValue: String) {
        _uiState.update { it.copy(username = newValue) }
    }

    fun onPasswordChange(newValue: String) {
        _uiState.update { it.copy(password = newValue) }
    }

    fun login() {
        val currentState = _uiState.value
        when {
            currentState.username.isBlank() || currentState.password.isBlank() -> {
                _uiState.update { 
                    it.copy(
                        errorMessage = "Please fill in all fields",
                        toastMessage = "Login failed: Missing fields"
                    ) 
                }
            }
            repository.validateCredentials(currentState.username, currentState.password) -> {
                _uiState.update { 
                    it.copy(
                        errorMessage = "",
                        loginSuccess = true,
                        toastMessage = "Welcome back, ${currentState.username}!"
                    ) 
                }
            }
            else -> {
                _uiState.update { 
                    it.copy(
                        errorMessage = "Invalid credentials",
                        toastMessage = "Login failed: Wrong username or password"
                    ) 
                }
            }
        }
    }

    fun logout() {
        _uiState.update { 
            it.copy(
                loginSuccess = false,
                username = "",
                password = "",
                errorMessage = "",
                toastMessage = "Logged out successfully"
            ) 
        }
    }

    fun clearToastMessage() {
        _uiState.update { it.copy(toastMessage = null) }
    }
}

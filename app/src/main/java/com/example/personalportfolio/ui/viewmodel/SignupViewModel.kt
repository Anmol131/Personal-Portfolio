package com.example.personalportfolio.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.personalportfolio.ui.state.SignupUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignupViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignupUiState())
    val uiState: StateFlow<SignupUiState> = _uiState.asStateFlow()

    fun onEmailChange(newValue: String) {
        _uiState.update { it.copy(email = newValue) }
    }

    fun onUsernameChange(newValue: String) {
        _uiState.update { it.copy(username = newValue) }
    }

    fun onPasswordChange(newValue: String) {
        _uiState.update { it.copy(password = newValue) }
    }

    fun onConfirmPasswordChange(newValue: String) {
        _uiState.update { it.copy(confirmPassword = newValue) }
    }

    fun signup() {
        val currentState = _uiState.value
        when {
            currentState.email.isBlank() || currentState.username.isBlank() || 
            currentState.password.isBlank() || currentState.confirmPassword.isBlank() -> {
                _uiState.update { it.copy(errorMessage = "Please fill in all fields") }
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(currentState.email).matches() -> {
                _uiState.update { it.copy(errorMessage = "Invalid email format") }
            }
            currentState.password != currentState.confirmPassword -> {
                _uiState.update { it.copy(errorMessage = "Passwords do not match") }
            }
            else -> {
                _uiState.update { it.copy(errorMessage = "", signupSuccess = true) }
            }
        }
    }
    
    fun resetSignupSuccess() {
        _uiState.update { it.copy(signupSuccess = false) }
    }
}

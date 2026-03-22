package com.example.personalportfolio.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.personalportfolio.ui.state.ChangePasswordUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ChangePasswordViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ChangePasswordUiState())
    val uiState: StateFlow<ChangePasswordUiState> = _uiState.asStateFlow()

    fun onOldPasswordChange(newValue: String) {
        _uiState.update { it.copy(oldPassword = newValue) }
    }

    fun onNewPasswordChange(newValue: String) {
        _uiState.update { it.copy(newPassword = newValue) }
    }

    fun onConfirmNewPasswordChange(newValue: String) {
        _uiState.update { it.copy(confirmNewPassword = newValue) }
    }

    fun updatePassword() {
        val currentState = _uiState.value
        when {
            currentState.oldPassword.isBlank() || currentState.newPassword.isBlank() || 
            currentState.confirmNewPassword.isBlank() -> {
                _uiState.update { it.copy(errorMessage = "Please fill in all fields") }
            }
            currentState.oldPassword != "password" -> { // Mock check
                _uiState.update { it.copy(errorMessage = "Incorrect old password") }
            }
            currentState.newPassword != currentState.confirmNewPassword -> {
                _uiState.update { it.copy(errorMessage = "New passwords do not match") }
            }
            else -> {
                _uiState.update { it.copy(errorMessage = "", isSuccess = true) }
            }
        }
    }
    
    fun resetSuccess() {
        _uiState.update { it.copy(isSuccess = false) }
    }
}

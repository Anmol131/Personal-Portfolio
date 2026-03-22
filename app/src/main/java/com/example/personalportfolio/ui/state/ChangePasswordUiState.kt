package com.example.personalportfolio.ui.state

data class ChangePasswordUiState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val confirmNewPassword: String = "",
    val errorMessage: String = "",
    val isSuccess: Boolean = false
)

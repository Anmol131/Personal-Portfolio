package com.example.personalportfolio.ui.state

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val errorMessage: String = "",
    val loginSuccess: Boolean = false,
    val toastMessage: String? = null
)

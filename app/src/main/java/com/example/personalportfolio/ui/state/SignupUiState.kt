package com.example.personalportfolio.ui.state

data class SignupUiState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorMessage: String = "",
    val signupSuccess: Boolean = false
)

package com.example.personalportfolio.model

data class User(
    val username: String,
    val email: String = "",
    val profileImageUri: String? = null
)

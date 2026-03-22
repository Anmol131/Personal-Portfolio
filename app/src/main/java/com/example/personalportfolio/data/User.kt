package com.example.personalportfolio.data

/**
 * Model class representing a User in the system.
 */
data class User(
    val username: String,
    val email: String = "",
    val profileImageUri: String? = null
)

package com.example.personalportfolio.model

/**
 * Repository class that handles data operations.
 */
class UserRepository {
    fun validateCredentials(username: String, password: String): Boolean {
        // Mock validation logic
        return username == "admin" && password == "password"
    }
}

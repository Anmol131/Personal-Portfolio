package com.example.personalportfolio.data

/**
 * Repository class that handles data operations.
 * In a real app, this would interact with a database or API.
 */
class UserRepository {
    fun validateCredentials(username: String, password: String): Boolean {
        // Mock validation logic
        return username == "admin" && password == "password"
    }
}

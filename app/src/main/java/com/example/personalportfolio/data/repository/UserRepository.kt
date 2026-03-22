package com.example.personalportfolio.data.repository

class UserRepository {
    fun validateCredentials(username: String, password: String): Boolean {
        return username == "admin" && password == "password"
    }
}

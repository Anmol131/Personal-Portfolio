package com.example.personalportfolio.ui.state

import com.example.personalportfolio.data.model.UserProfile

data class HomeUiState(
    val userProfile: UserProfile = UserProfile(),
    val showLogoutDialog: Boolean = false
)

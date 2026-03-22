package com.example.personalportfolio.ui.state

import com.example.personalportfolio.data.model.UserProfile

data class EditProfileUiState(
    val userProfile: UserProfile = UserProfile(),
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val selectedImageUri: String? = null
)

package com.example.personalportfolio.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.personalportfolio.data.model.UserProfile
import com.example.personalportfolio.data.repository.ProfileRepository
import com.example.personalportfolio.ui.state.EditProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState: StateFlow<EditProfileUiState> = _uiState.asStateFlow()

    init {
        val profile = repository.getUserProfile()
        _uiState.update { it.copy(userProfile = profile, selectedImageUri = profile.profileImageUri) }
    }

    fun onNameChange(newName: String) {
        _uiState.update { it.copy(userProfile = it.userProfile.copy(name = newName)) }
    }

    fun onBioChange(newBio: String) {
        _uiState.update { it.copy(userProfile = it.userProfile.copy(bio = newBio)) }
    }

    fun onQualificationChange(newQual: String) {
        _uiState.update { it.copy(userProfile = it.userProfile.copy(qualification = newQual)) }
    }

    fun onEducationChange(newEdu: String) {
        _uiState.update { it.copy(userProfile = it.userProfile.copy(education = newEdu)) }
    }

    fun onExperienceChange(newExp: String) {
        _uiState.update { it.copy(userProfile = it.userProfile.copy(experience = newExp)) }
    }

    fun onSkillsChange(newSkills: String) {
        _uiState.update { it.copy(userProfile = it.userProfile.copy(skills = newSkills)) }
    }

    fun onContactChange(newContact: String) {
        _uiState.update { it.copy(userProfile = it.userProfile.copy(contact = newContact)) }
    }

    fun onImageSelected(uri: String) {
        _uiState.update { it.copy(selectedImageUri = uri) }
    }

    fun saveProfile() {
        val currentState = _uiState.value
        repository.saveUserProfile(currentState.userProfile, currentState.selectedImageUri)
        _uiState.update { it.copy(isSaved = true) }
    }
    
    fun resetSavedState() {
        _uiState.update { it.copy(isSaved = false) }
    }
}

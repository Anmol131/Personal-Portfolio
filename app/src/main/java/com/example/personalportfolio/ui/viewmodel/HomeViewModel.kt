package com.example.personalportfolio.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.personalportfolio.data.repository.ProfileRepository
import com.example.personalportfolio.ui.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(private val repository: ProfileRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        refreshProfile()
    }

    fun refreshProfile() {
        val profile = repository.getUserProfile()
        _uiState.update { it.copy(userProfile = profile) }
    }

    fun setShowLogoutDialog(show: Boolean) {
        _uiState.update { it.copy(showLogoutDialog = show) }
    }
}

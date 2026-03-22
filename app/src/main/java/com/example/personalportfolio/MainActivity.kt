package com.example.personalportfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.personalportfolio.data.repository.ProfileRepository
import com.example.personalportfolio.data.repository.UserRepository
import com.example.personalportfolio.ui.screens.*
import com.example.personalportfolio.ui.theme.PersonalPortfolioTheme
import com.example.personalportfolio.ui.viewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PersonalPortfolioTheme {
                val context = LocalContext.current
                
                // Repositories
                val profileRepository = remember { ProfileRepository(context) }
                val userRepository = remember { UserRepository() }

                // ViewModels with Factories
                val homeViewModel: HomeViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return HomeViewModel(profileRepository) as T
                        }
                    }
                )
                
                val loginViewModel: LoginViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return LoginViewModel(userRepository) as T
                        }
                    }
                )

                val editProfileViewModel: EditProfileViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return EditProfileViewModel(profileRepository) as T
                        }
                    }
                )

                val signupViewModel: SignupViewModel = viewModel()
                val changePasswordViewModel: ChangePasswordViewModel = viewModel()

                var currentScreen by remember { mutableStateOf("login") }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (currentScreen) {
                        "login" -> LoginScreen(
                            viewModel = loginViewModel,
                            onLoginSuccess = { 
                                homeViewModel.refreshProfile()
                                currentScreen = "home" 
                            },
                            onSignupClick = { currentScreen = "signup" }
                        )
                        "signup" -> SignupScreen(
                            viewModel = signupViewModel,
                            onSignupSuccess = { currentScreen = "login" },
                            onLoginClick = { currentScreen = "login" }
                        )
                        "home" -> HomeScreen(
                            viewModel = homeViewModel,
                            onLogout = { 
                                loginViewModel.logout()
                                currentScreen = "login" 
                            },
                            onEditClick = { currentScreen = "edit" },
                            onChangePasswordClick = { currentScreen = "change_password" }
                        )
                        "edit" -> EditProfileScreen(
                            viewModel = editProfileViewModel,
                            onBack = { 
                                homeViewModel.refreshProfile()
                                currentScreen = "home" 
                            }
                        )
                        "change_password" -> ChangePasswordScreen(
                            viewModel = changePasswordViewModel,
                            onBack = { currentScreen = "home" },
                            onSuccess = { currentScreen = "home" }
                        )
                    }
                }
            }
        }
    }
}

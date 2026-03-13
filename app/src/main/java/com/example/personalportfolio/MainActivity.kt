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
import com.example.personalportfolio.ui.theme.PersonalPortfolioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PersonalPortfolioTheme {
                var currentScreen by remember { mutableStateOf("login") }
                
                // Profile State - Persistent for the session
                var name by remember { mutableStateOf("John Doe") }
                var bio by remember { mutableStateOf("Android Developer | UI/UX Enthusiast") }
                var qualification by remember { mutableStateOf("B.Tech in Computer Science") }
                var education by remember { mutableStateOf("Stanford University (2018 - 2022)") }
                var experience by remember { mutableStateOf("Software Engineer at Tech Corp (2022 - Present)") }
                var skills by remember { mutableStateOf("Kotlin, Java, Jetpack Compose, Firebase, Git") }
                var contact by remember { mutableStateOf("Email: john.doe@example.com\nLinkedIn: linkedin.com/in/johndoe") }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (currentScreen) {
                        "login" -> LoginScreen(
                            onLoginSuccess = { currentScreen = "home" },
                            onSignupClick = { currentScreen = "signup" }
                        )
                        "signup" -> SignupScreen(
                            onSignupSuccess = { currentScreen = "login" },
                            onLoginClick = { currentScreen = "login" }
                        )
                        "home" -> HomeScreen(
                            name = name,
                            bio = bio,
                            qualification = qualification,
                            education = education,
                            experience = experience,
                            skills = skills,
                            contact = contact,
                            onLogout = { currentScreen = "login" },
                            onEditClick = { currentScreen = "edit" },
                            onChangePasswordClick = { currentScreen = "change_password" }
                        )
                        "edit" -> EditProfileScreen(
                            initialName = name,
                            initialBio = bio,
                            initialQualification = qualification,
                            initialEducation = education,
                            initialExperience = experience,
                            initialSkills = skills,
                            initialContact = contact,
                            onBack = { currentScreen = "home" },
                            onSave = { n: String, b: String, q: String, ed: String, ex: String, s: String, c: String ->
                                name = n
                                bio = b
                                qualification = q
                                education = ed
                                experience = ex
                                skills = s
                                contact = c
                                currentScreen = "home"
                            }
                        )
                        "change_password" -> ChangePasswordScreen(
                            onBack = { currentScreen = "home" },
                            onSuccess = { currentScreen = "home" }
                        )
                    }
                }
            }
        }
    }
}

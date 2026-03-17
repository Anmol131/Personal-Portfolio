package com.example.personalportfolio

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.personalportfolio.ui.theme.PersonalPortfolioTheme
import java.io.File
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PersonalPortfolioTheme {
                val context = LocalContext.current
                val sharedPreferences = remember { 
                    context.getSharedPreferences("portfolio_prefs", Context.MODE_PRIVATE) 
                }

                val loginViewModel: LoginViewModel = viewModel()
                var currentScreen by remember { mutableStateOf("login") }
                
                // Profile State - Persistent using SharedPreferences
                var name by remember { 
                    mutableStateOf(sharedPreferences.getString("name", "John Doe") ?: "John Doe") 
                }
                var bio by remember { 
                    mutableStateOf(sharedPreferences.getString("bio", "Android Developer | UI/UX Enthusiast") ?: "Android Developer | UI/UX Enthusiast") 
                }
                var qualification by remember { 
                    mutableStateOf(sharedPreferences.getString("qualification", "B.Tech in Computer Science") ?: "B.Tech in Computer Science") 
                }
                var education by remember { 
                    mutableStateOf(sharedPreferences.getString("education", "Stanford University (2018 - 2022)") ?: "Stanford University (2018 - 2022)") 
                }
                var experience by remember { 
                    mutableStateOf(sharedPreferences.getString("experience", "Software Engineer at Tech Corp (2022 - Present)") ?: "Software Engineer at Tech Corp (2022 - Present)") 
                }
                var skills by remember { 
                    mutableStateOf(sharedPreferences.getString("skills", "Kotlin, Java, Jetpack Compose, Firebase, Git") ?: "Kotlin, Java, Jetpack Compose, Firebase, Git") 
                }
                var contact by remember { 
                    mutableStateOf(sharedPreferences.getString("contact", "Email: john.doe@example.com\nLinkedIn: linkedin.com/in/johndoe") ?: "Email: john.doe@example.com\nLinkedIn: linkedin.com/in/johndoe") 
                }
                var profileImageUri by remember {
                    mutableStateOf(sharedPreferences.getString("profile_image", null))
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (currentScreen) {
                        "login" -> LoginScreen(
                            viewModel = loginViewModel,
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
                            profileImageUri = profileImageUri,
                            onLogout = { 
                                loginViewModel.logout() // Reset the login state
                                currentScreen = "login" 
                            },
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
                            initialImageUri = profileImageUri,
                            onBack = { currentScreen = "home" },
                            onSave = { n: String, b: String, q: String, ed: String, ex: String, s: String, c: String, img: String? ->
                                // Handle Image Persistence
                                var finalImgPath = img
                                if (img != null && img.startsWith("content://")) {
                                    try {
                                        val inputStream = context.contentResolver.openInputStream(img.toUri())
                                        if (inputStream != null) {
                                            val fileName = "profile_pic_${System.currentTimeMillis()}.jpg"
                                            val file = File(context.filesDir, fileName)
                                            
                                            // Clean up old profile pics
                                            context.filesDir.listFiles { f -> f.name.startsWith("profile_pic_") }?.forEach { it.delete() }
                                            
                                            FileOutputStream(file).use { outputStream ->
                                                inputStream.copyTo(outputStream)
                                            }
                                            finalImgPath = file.absolutePath
                                            Log.d("Portfolio", "Image saved to: $finalImgPath")
                                        }
                                    } catch (e: Exception) {
                                        Log.e("Portfolio", "Error saving image", e)
                                    }
                                }

                                // Update local state
                                name = n
                                bio = b
                                qualification = q
                                education = ed
                                experience = ex
                                skills = s
                                contact = c
                                profileImageUri = finalImgPath
                                
                                // Save to SharedPreferences
                                sharedPreferences.edit().apply {
                                    putString("name", n)
                                    putString("bio", b)
                                    putString("qualification", q)
                                    putString("education", ed)
                                    putString("experience", ex)
                                    putString("skills", s)
                                    putString("contact", c)
                                    putString("profile_image", finalImgPath)
                                    apply()
                                }

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

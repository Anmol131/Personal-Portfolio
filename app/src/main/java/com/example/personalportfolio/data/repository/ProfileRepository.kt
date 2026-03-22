package com.example.personalportfolio.data.repository

import android.content.Context
import android.net.Uri
import com.example.personalportfolio.data.model.UserProfile
import java.io.File
import java.io.FileOutputStream

class ProfileRepository(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("portfolio_prefs", Context.MODE_PRIVATE)

    fun getUserProfile(): UserProfile {
        return UserProfile(
            name = sharedPreferences.getString("name", "John Doe") ?: "John Doe",
            bio = sharedPreferences.getString("bio", "Android Developer | UI/UX Enthusiast") ?: "Android Developer | UI/UX Enthusiast",
            qualification = sharedPreferences.getString("qualification", "B.Tech in Computer Science") ?: "B.Tech in Computer Science",
            education = sharedPreferences.getString("education", "Stanford University (2018 - 2022)") ?: "Stanford University (2018 - 2022)",
            experience = sharedPreferences.getString("experience", "Software Engineer at Tech Corp (2022 - Present)") ?: "Software Engineer at Tech Corp (2022 - Present)",
            skills = sharedPreferences.getString("skills", "Kotlin, Java, Jetpack Compose, Firebase, Git") ?: "Kotlin, Java, Jetpack Compose, Firebase, Git",
            contact = sharedPreferences.getString("contact", "Email: john.doe@example.com\nLinkedIn: linkedin.com/in/johndoe") ?: "Email: john.doe@example.com\nLinkedIn: linkedin.com/in/johndoe",
            profileImageUri = sharedPreferences.getString("profile_image", null)
        )
    }

    fun saveUserProfile(profile: UserProfile, newImageUri: String?) {
        var finalImgPath = profile.profileImageUri
        
        if (newImageUri != null && newImageUri.startsWith("content://")) {
            finalImgPath = saveImageToInternalStorage(newImageUri)
        }

        sharedPreferences.edit().apply {
            putString("name", profile.name)
            putString("bio", profile.bio)
            putString("qualification", profile.qualification)
            putString("education", profile.education)
            putString("experience", profile.experience)
            putString("skills", profile.skills)
            putString("contact", profile.contact)
            putString("profile_image", finalImgPath)
            apply()
        }
    }

    private fun saveImageToInternalStorage(uriString: String): String? {
        return try {
            val inputStream = context.contentResolver.openInputStream(Uri.parse(uriString))
            if (inputStream != null) {
                val fileName = "profile_pic_${System.currentTimeMillis()}.jpg"
                val file = File(context.filesDir, fileName)
                
                // Clean up old profile pics
                context.filesDir.listFiles { f -> f.name.startsWith("profile_pic_") }?.forEach { it.delete() }
                
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
                file.absolutePath
            } else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

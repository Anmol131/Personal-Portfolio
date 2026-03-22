package com.example.personalportfolio.data.model

data class UserProfile(
    val name: String = "John Doe",
    val bio: String = "Android Developer | UI/UX Enthusiast",
    val qualification: String = "B.Tech in Computer Science",
    val education: String = "Stanford University (2018 - 2022)",
    val experience: String = "Software Engineer at Tech Corp (2022 - Present)",
    val skills: String = "Kotlin, Java, Jetpack Compose, Firebase, Git",
    val contact: String = "Email: john.doe@example.com\nLinkedIn: linkedin.com/in/johndoe",
    val profileImageUri: String? = null
)

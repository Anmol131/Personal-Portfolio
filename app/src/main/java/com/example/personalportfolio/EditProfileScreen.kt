package com.example.personalportfolio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    initialName: String,
    initialBio: String,
    initialQualification: String,
    initialEducation: String,
    initialExperience: String,
    initialSkills: String,
    initialContact: String,
    onBack: () -> Unit, 
    onSave: (String, String, String, String, String, String, String) -> Unit
) {
    var name by remember { mutableStateOf(initialName) }
    var bio by remember { mutableStateOf(initialBio) }
    var qualification by remember { mutableStateOf(initialQualification) }
    var education by remember { mutableStateOf(initialEducation) }
    var experience by remember { mutableStateOf(initialExperience) }
    var skills by remember { mutableStateOf(initialSkills) }
    var contact by remember { mutableStateOf(initialContact) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Profile") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth())
            }
            item {
                OutlinedTextField(value = bio, onValueChange = { bio = it }, label = { Text("Short Bio") }, modifier = Modifier.fillMaxWidth())
            }
            item {
                OutlinedTextField(value = qualification, onValueChange = { qualification = it }, label = { Text("Qualification") }, modifier = Modifier.fillMaxWidth())
            }
            item {
                OutlinedTextField(value = education, onValueChange = { education = it }, label = { Text("Education") }, modifier = Modifier.fillMaxWidth())
            }
            item {
                OutlinedTextField(value = experience, onValueChange = { experience = it }, label = { Text("Experience") }, modifier = Modifier.fillMaxWidth())
            }
            item {
                OutlinedTextField(value = skills, onValueChange = { skills = it }, label = { Text("Skills") }, modifier = Modifier.fillMaxWidth())
            }
            item {
                OutlinedTextField(value = contact, onValueChange = { contact = it }, label = { Text("Contact Info") }, modifier = Modifier.fillMaxWidth())
            }
            item {
                Button(
                    onClick = { onSave(name, bio, qualification, education, experience, skills, contact) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Changes")
                }
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

package com.example.personalportfolio.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

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
    initialImageUri: String?,
    onBack: () -> Unit, 
    onSave: (String, String, String, String, String, String, String, String?) -> Unit
) {
    var name by remember { mutableStateOf(initialName) }
    var bio by remember { mutableStateOf(initialBio) }
    var qualification by remember { mutableStateOf(initialQualification) }
    var education by remember { mutableStateOf(initialEducation) }
    var experience by remember { mutableStateOf(initialExperience) }
    var skills by remember { mutableStateOf(initialSkills) }
    var contact by remember { mutableStateOf(initialContact) }
    var imageUri by remember { mutableStateOf(initialImageUri) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri = it.toString() }
    }

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
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                ) {
                    if (imageUri != null) {
                        AsyncImage(
                            model = imageUri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                TextButton(onClick = { launcher.launch("image/*") }) {
                    Text("Change Photo")
                }
            }
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
                    onClick = { onSave(name, bio, qualification, education, experience, skills, contact, imageUri) },
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

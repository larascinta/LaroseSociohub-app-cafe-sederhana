package com.example.larosesociohub.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.larosesociohub.data.SharedPreferencesManager
import com.example.larosesociohub.ui.theme.RosePink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val sharedPrefs = remember { SharedPreferencesManager(context) }

    var name by remember { mutableStateOf(sharedPrefs.restaurantName) }
    var address by remember { mutableStateOf(sharedPrefs.address) }
    var description by remember { mutableStateOf(sharedPrefs.description) }
    var openHours by remember { mutableStateOf(sharedPrefs.openHours) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Edit Profil", fontWeight = FontWeight.Bold, color = RosePink) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Batal", tint = RosePink)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White.copy(alpha = 0.9f)
                )
            )
        },
        containerColor = Color(0xFFFFF5F7)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            EditTextField(label = "Nama Cafe", value = name, onValueChange = { name = it })
            EditTextField(label = "Alamat", value = address, onValueChange = { address = it })
            EditTextField(label = "Deskripsi", value = description, onValueChange = { description = it }, singleLine = false)
            EditTextField(label = "Jam Buka", value = openHours, onValueChange = { openHours = it })

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    sharedPrefs.restaurantName = name
                    sharedPrefs.address = address
                    sharedPrefs.description = description
                    sharedPrefs.openHours = openHours
                    onBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RosePink),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Simpan Perubahan", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, RosePink),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = RosePink)
            ) {
                Text("Batal", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun EditTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = RosePink,
                unfocusedBorderColor = RosePink.copy(alpha = 0.5f),
                cursorColor = RosePink
            ),
            singleLine = singleLine,
            minLines = if (singleLine) 1 else 3
        )
    }
}

package com.example.myapplication.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(
    videoUrl: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🔹 Aquí irá el VIDEO (más adelante)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Text(text = "Video placeholder")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Texto descriptivo
        Text(
            text = "Descripción del video\n\nURL:\n$videoUrl"
        )
    }
}
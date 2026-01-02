package com.example.myapplication.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.screen.player.VideoPlayer

@Composable
fun DetailScreen(videoUrl: String) {
    Column(modifier = Modifier.fillMaxSize()) {

        VideoPlayer(
            videoUrl = videoUrl,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Descripción del video\n\n$videoUrl",
            modifier = Modifier.padding(16.dp)
        )
    }
}

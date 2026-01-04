package com.example.myapplication.screen.detail

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.screen.player.PlayerViewModel
import com.example.myapplication.screen.player.VideoPlayer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material3.Icon

@Composable
fun DetailScreen(
    videoUrl: String,
    navController: NavController,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    LaunchedEffect(videoUrl) {
        viewModel.setMedia(videoUrl)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        VideoPlayer(
            player = viewModel.player,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

        IconButton(
            onClick = {
                navController.navigate("fullscreen/${Uri.encode(videoUrl)}")
            }
        ) {
            Icon(
                imageVector = Icons.Default.Fullscreen,
                contentDescription = "Fullscreen"
            )
        }

        Text(
            text = "Descripción del video",
            modifier = Modifier.padding(16.dp)
        )
    }
}


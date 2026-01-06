package com.example.myapplication.screen.detail

import android.app.Activity
import android.content.pm.ActivityInfo
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material3.Icon
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.core.navigation.Routes

import androidx.activity.compose.BackHandler

@Composable
fun DetailScreen(
    videoUrl: String,
    navController: NavController,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    var isFullscreen by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(videoUrl) {
        viewModel.setMediaIfNeeded(videoUrl)
    }

    BackHandler(enabled = isFullscreen) {
        isFullscreen = false
    }

    DisposableEffect(isFullscreen) {
        activity.requestedOrientation =
            if (isFullscreen)
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            else
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        onDispose {
            activity.requestedOrientation =
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        VideoPlayer(
            player = viewModel.player,
            modifier = if (isFullscreen) {
                Modifier.fillMaxSize()
            } else {
                Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            }
        )

        if (!isFullscreen) {
            IconButton(
                onClick = { isFullscreen = true }
            ) {
                Icon(Icons.Default.Fullscreen, contentDescription = "Fullscreen")
            }

            Text(
                text = "Descripción del video",
                modifier = Modifier.padding(16.dp)
            )
        } else {
            IconButton(
                onClick = { isFullscreen = false },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Exit fullscreen")
            }
        }
    }
}

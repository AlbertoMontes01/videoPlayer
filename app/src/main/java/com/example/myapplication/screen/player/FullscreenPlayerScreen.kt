package com.example.myapplication.screen.player

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun FullscreenPlayerScreen(
    videoUrl: String,
    navController: NavController,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity

    LaunchedEffect(videoUrl) {
        viewModel.restorePosition()
    }

    DisposableEffect(Unit) {
        activity.requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        onDispose {
            activity.requestedOrientation =
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        VideoPlayer(
            player = viewModel.player,
            modifier = Modifier.fillMaxSize()
        )

        IconButton(
            onClick = {
                viewModel.savePosition()
                navController.popBackStack()
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(Icons.Default.Close, contentDescription = "Exit fullscreen")
        }
    }
}


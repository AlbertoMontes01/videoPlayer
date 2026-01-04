package com.example.myapplication.screen.player

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FullscreenPlayerScreen(
    videoUrl: String,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity

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
    }
}

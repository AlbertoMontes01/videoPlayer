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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.platform.LocalContext

import androidx.activity.compose.BackHandler
import com.example.myapplication.MainActivity

@Composable
fun DetailScreen(
    videoUrl: String,
    navController: NavController,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    var isFullscreen by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    val activity = context as? MainActivity
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                activity?.let {
                    if (!it.isInPictureInPictureMode) {
                        it.enterPipMode()
                    }
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            if (!activity?.isInPictureInPictureMode!!) {
                viewModel.player.pause()
                viewModel.player.clearMediaItems()
            }
        }
    }

    LaunchedEffect(videoUrl) {
        viewModel.setMediaIfNeeded(videoUrl)
    }

    BackHandler(enabled = isFullscreen) {
        isFullscreen = false
    }

    DisposableEffect(isFullscreen) {
        activity?.requestedOrientation =
            if (isFullscreen)
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            else
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        onDispose {
            activity?.requestedOrientation =
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

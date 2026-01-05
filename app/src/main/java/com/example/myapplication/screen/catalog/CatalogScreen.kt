package com.example.myapplication.screen.catalog

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.domain.model.Video
import com.example.myapplication.screen.player.PlayerViewModel

@Composable
fun CatalogScreen(
    navController: NavController,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    val videos = listOf(
        Video(
            title = "Big Buck Bunny",
            url = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
        ),
        Video(
            title = "Elephant Dream",
            url = "https://storage.googleapis.com/exoplayer-test-media-0/ElephantsDream.mp4"
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(videos) { video ->
            Text(
                text = video.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.setMedia(video.url)
                        navController.navigate(
                            "detail/${Uri.encode(video.url)}"
                        )
                    }
                    .padding(16.dp)
            )
        }
    }
}

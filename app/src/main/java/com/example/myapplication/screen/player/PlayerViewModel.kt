package com.example.myapplication.screen.player

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val context = getApplication<Application>()

    val player: ExoPlayer by lazy {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
        }
    }

    fun setMedia(url: String) {
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}

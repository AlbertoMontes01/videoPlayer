package com.example.myapplication.screen.player

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {

    val player = ExoPlayer.Builder(context).build()

    private var currentUrl: String? = null
    private var lastPosition: Long = 0L

    fun setMedia(url: String) {
        if (currentUrl == url && player.mediaItemCount > 0) return

        currentUrl = url
        player.setMediaItem(MediaItem.fromUri(url))
        player.prepare()
        player.playWhenReady = true
    }

    fun savePosition() {
        lastPosition = player.currentPosition
    }

    fun restorePosition() {
        player.seekTo(lastPosition)
    }

    override fun onCleared() {
        player.release()
    }
}

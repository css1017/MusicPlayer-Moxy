package com.css101.musicplayermoxy.presentation.ui.player

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.css101.musicplayermoxy.domain.models.AudioFile
import moxy.MvpPresenter

class PlayerPresenter(private val player: ExoPlayer) : MvpPresenter<PlayerView>() {

    init {
        addListener()
    }

    private var localAudio: AudioFile? = null
    private var isMusicPlaying = false

    fun saveAudio(audio: AudioFile?) = audio?.let {
        if (localAudio != it) {
            localAudio = it
            setMediaItem(it)
            playAudio()
        }
    }

    private fun setMediaItem(audio: AudioFile) {
        val mediaItem = MediaItem.Builder()
            .setUri(audio.fileUri)
            .build()
        player.setMediaItem(mediaItem)
    }

    fun playPause() {
        when (isMusicPlaying) {
            false -> playAudio()
            true -> pausePlayback()
        }
    }


    private fun addListener() {
        player.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                isMusicPlaying = isPlaying
                viewState.setControls(isMusicPlaying)
            }

            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_ENDED) {
                    localAudio?.let {
                        setMediaItem(localAudio!!)
                        pausePlayback()
                    }
                }
            }
        })
    }

    private fun playAudio() {
        player.prepare()
        player.playWhenReady = true
        isMusicPlaying = true
        viewState.showPlayer()
        viewState.setAudio(localAudio)
        viewState.setControls(isMusicPlaying)
    }

    private fun pausePlayback() {
        player.playWhenReady = false
        isMusicPlaying = false
        viewState.setControls(isMusicPlaying)
    }
}
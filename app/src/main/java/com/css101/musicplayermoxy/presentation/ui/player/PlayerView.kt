package com.css101.musicplayermoxy.presentation.ui.player

import com.css101.musicplayermoxy.domain.models.AudioFile
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PlayerView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showEmpty()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showPlayer()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAudio(audio: AudioFile?)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setControls(isPlaying: Boolean)
}
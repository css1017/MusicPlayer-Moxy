package com.css101.musicplayermoxy.presentation.ui.list

import com.css101.musicplayermoxy.domain.models.AudioFile
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ListView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showEmpty()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showList()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAdapter(musicList: List<AudioFile>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showNoPermission()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun requestPermission()
}
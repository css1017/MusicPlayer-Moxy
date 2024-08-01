package com.css101.musicplayermoxy.di

import com.css101.musicplayermoxy.presentation.ui.list.ListPresenter
import com.css101.musicplayermoxy.presentation.ui.player.PlayerPresenter
import org.koin.dsl.module

val appModule = module {
    single { PlayerPresenter(get()) }
    single { ListPresenter(get()) }
}
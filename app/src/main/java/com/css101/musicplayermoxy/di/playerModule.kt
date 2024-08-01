package com.css101.musicplayermoxy.di

import androidx.media3.exoplayer.ExoPlayer
import org.koin.dsl.module

val playerModule = module {

  single { ExoPlayer.Builder(get()).build() }
}
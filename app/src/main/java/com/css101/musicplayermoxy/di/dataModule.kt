package com.css101.musicplayermoxy.di

import com.css101.musicplayermoxy.data.repository.MusicRepoImpl
import com.css101.musicplayermoxy.domain.repository.MusicRepo
import org.koin.dsl.module

val dataModule = module {
    single<MusicRepo> { MusicRepoImpl(get()) }
}
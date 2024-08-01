package com.css101.musicplayermoxy.di

import com.css101.musicplayermoxy.domain.usecase.GetMusicListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetMusicListUseCase(get()) }
}
package com.css101.musicplayermoxy.app

import android.app.Application
import com.css101.musicplayermoxy.di.appModule
import com.css101.musicplayermoxy.di.dataModule
import com.css101.musicplayermoxy.di.domainModule
import com.css101.musicplayermoxy.di.playerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, domainModule, dataModule, playerModule)
        }
    }
}
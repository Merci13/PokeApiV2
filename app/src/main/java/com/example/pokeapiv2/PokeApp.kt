package com.example.pokeapiv2

import android.app.Application
import com.example.pokeapiv2.dependencyInjection.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@PokeApp)
            modules(appModule)
        }
    }

}
package com.onats.rickandmorty.app

import android.app.Application
import com.onats.rickandmorty.BuildConfig
import timber.log.Timber

class RickAndMortyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.Forest.plant(Timber.DebugTree())
        }
    }
}
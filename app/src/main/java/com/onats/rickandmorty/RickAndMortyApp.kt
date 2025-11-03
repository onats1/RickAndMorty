package com.onats.rickandmorty

import android.app.Application
import timber.log.Timber

class RickAndMortyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
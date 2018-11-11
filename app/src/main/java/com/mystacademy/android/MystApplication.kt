package com.mystacademy.android

import android.app.Application
import com.mystacademy.android.network.NetworkClientHolder
import com.mystacademy.android.repositories.sharedPreferences.SharedPreferencesRepositoryImpl
import timber.log.Timber

class MystApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initTimber()
        initPreferencesAndNetwork()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initPreferencesAndNetwork() {
        SharedPreferencesRepositoryImpl.init(this)
        NetworkClientHolder.init(SharedPreferencesRepositoryImpl.getInstance())
    }

}
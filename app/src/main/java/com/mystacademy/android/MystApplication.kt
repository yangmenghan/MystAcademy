package com.mystacademy.android

import android.app.Application
import timber.log.Timber

/**
 * Created by menghan on 30/03/2018.
 */
class MystApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    // Timber Config
    configTimber()
  }

  private fun configTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}
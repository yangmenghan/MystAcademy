package com.mystacademy.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View

class SplashActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

    // Route to main activity
    Handler().postDelayed({
      startActivity(Intent(this, MainActivity::class.java))
    }, 2000)
  }
}

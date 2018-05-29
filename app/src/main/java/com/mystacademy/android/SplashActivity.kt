package com.mystacademy.android

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.Group
import android.view.View

class SplashActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

    // Route to main activity
    Handler().postDelayed({
      switchToLogin()

    }, 2000)
  }

  private fun switchToLogin() {

    findViewById<View>(R.id.splash_divider).visibility = View.GONE
    findViewById<Group>(R.id.login_group).visibility = View.VISIBLE

  }
  //      startActivity(Intent(this, MainActivity::class.java))
}

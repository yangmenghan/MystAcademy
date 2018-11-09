package com.mystacademy.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.constraint.Group
import android.view.View
import com.apollographql.apollo.ApolloCall.Callback
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.mystacademy.android.LoginQueryMutation.Data
import com.mystacademy.android.R.string
import com.mystacademy.android.type.LoginInput
import com.mystacademy.android.utils.ApolloClientManager
import kotlinx.android.synthetic.main.activity_splash.login_b
import kotlinx.android.synthetic.main.activity_splash.login_password_et
import kotlinx.android.synthetic.main.activity_splash.login_password_til
import kotlinx.android.synthetic.main.activity_splash.login_userName_et
import kotlinx.android.synthetic.main.activity_splash.login_userName_til

class SplashActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    login_b.setOnClickListener { checkLoginInput() }

    // Route to main activity
    routeToMain()

    // TODO : Remove line above
//    Handler().postDelayed({
//      switchToLogin()
//
//    }, 2000)
  }

  private fun switchToLogin() {

    findViewById<View>(R.id.splash_divider).visibility = View.GONE
    findViewById<Group>(R.id.login_group).visibility = View.VISIBLE

  }

  private fun checkLoginInput() {
    val username = login_userName_et.text.toString()
    val password = login_password_et.text.toString()

    if (username.isEmpty()) {
      showUsernameEmptyError()
    } else if (password.isEmpty()) {
      showLoginEmptyError()
    } else {
      login(username, password)
    }
  }

  private fun login(username: String, password: String) {
    ApolloClientManager.getClient().mutate(
      LoginQueryMutation(
        LoginInput.builder().username(username).password(password).build()
      )
    ).enqueue(loginCallback)
  }

  private val loginCallback = object : Callback<Data>() {
    override fun onFailure(e: ApolloException) {
      e.printStackTrace()
    }

    override fun onResponse(response: Response<Data>) {
      runOnUiThread {
        if (!response.hasErrors()) {
          routeToMain()
        } else {
          showLoginError()
        }
      }
    }
  }

  private fun routeToMain() {
    startActivity(Intent(this, MainActivity::class.java))
  }

  // view
  private fun showUsernameEmptyError() {
    login_userName_til.error = getString(string.error_login_username_empty)
  }

  private fun showLoginEmptyError() {
    login_password_til.error = getString(string.error_login_password_empty)
  }

  private fun showLoginError() {
    login_password_til.error = getString(string.error_login_failed)
  }


}

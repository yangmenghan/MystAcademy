package com.mystacademy.android

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.Group
import android.view.View
import com.apollographql.apollo.ApolloCall.Callback
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.mystacademy.android.R.string
import com.mystacademy.android.flowController.FlowControllerImpl
import com.mystacademy.android.repositories.sharedPreferences.SharedPreferencesRepositoryImpl
import com.mystacademy.android.type.LoginInput
import com.mystacademy.android.utils.ApolloClientFactory
import kotlinx.android.synthetic.main.splash_activity.login_b
import kotlinx.android.synthetic.main.splash_activity.login_password_et
import kotlinx.android.synthetic.main.splash_activity.login_password_til
import kotlinx.android.synthetic.main.splash_activity.login_userName_et
import kotlinx.android.synthetic.main.splash_activity.login_userName_til

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        login_b.setOnClickListener { checkLoginInput() }

        Handler().postDelayed({
            switchToLogin()

        }, 2000)
    }

    private fun switchToLogin() {

        findViewById<View>(R.id.splash_divider).visibility = View.GONE
        findViewById<Group>(R.id.login_group).visibility = View.VISIBLE

    }

    private fun checkLoginInput() {
        val username = login_userName_et.text.toString()
        val password = login_password_et.text.toString()

        when {
            username.isEmpty() -> showUsernameEmptyError()
            password.isEmpty() -> showLoginEmptyError()
            else -> login(username, password)
        }
    }

    private fun login(username: String, password: String) {
        val preferences = SharedPreferencesRepositoryImpl.getInstance(applicationContext)
        ApolloClientFactory().getClient(preferences).mutate(
            LoginMutation(
                LoginInput.builder().username(username).password(password).build()
            )
        ).enqueue(loginCallback)
    }

    private val loginCallback = object : Callback<LoginMutation.Data>() {
        override fun onFailure(e: ApolloException) {
            e.printStackTrace()
        }

        override fun onResponse(response: Response<LoginMutation.Data>) {
            runOnUiThread {
                if (!response.hasErrors() && response.data()?.login?.token != null) {
                    SharedPreferencesRepositoryImpl.getInstance(applicationContext)
                        .setToken(response.data()?.login?.token)
                    routeToMain()
                } else {
                    showLoginError()
                }
            }
        }
    }

    private fun routeToMain() {
        FlowControllerImpl().routeToMain(this)
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

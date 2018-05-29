package com.mystacademy.android

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.mystacademy.android.LoginQueryMutation.Data
import kotlinx.android.synthetic.main.activity_main.message
import kotlinx.android.synthetic.main.activity_main.navigation

class MainActivity : AppCompatActivity() {

  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      R.id.navigation_home -> {
        message.setText(R.string.title_home)
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_dashboard -> {
        message.setText(R.string.title_dashboard)
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_profile -> {
        message.setText(R.string.title_profile)
        return@OnNavigationItemSelectedListener true
      }
    }


    false
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
  }



  }

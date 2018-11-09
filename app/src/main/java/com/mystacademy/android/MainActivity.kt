package com.mystacademy.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

//  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//    when (item.itemId) {
//      R.id.navigation_home -> {
//        message.setText(R.string.title_home)
//        return@OnNavigationItemSelectedListener true
//      }
//      R.id.navigation_dashboard -> {
//        message.setText(R.string.title_dashboard)
//        return@OnNavigationItemSelectedListener true
//      }
//      R.id.navigation_profile -> {
//        message.setText(R.string.title_profile)
//        return@OnNavigationItemSelectedListener true
//      }
//    }
//
//
//    false
//  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.fragment_user_profile)

//    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
  }


}

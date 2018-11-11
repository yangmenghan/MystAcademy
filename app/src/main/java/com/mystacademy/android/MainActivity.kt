package com.mystacademy.android

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.mystacademy.android.modules.user_profile.UserProfileFragment
import kotlinx.android.synthetic.main.main_activity.navigation

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                fragmentManager.beginTransaction().replace(R.id.main_content_frame_layout,
                    UserProfileFragment()
                )
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


}

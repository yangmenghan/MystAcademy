package com.mystacademy.android.modules.user_profile

import android.view.View
import com.mystacademy.android.UserProfileQuery.Viewer

interface UserProfileContract {
    interface View {

        fun displayUserProfile(userProfile: Viewer, view: android.view.View)
    }

    interface Presenter {

        fun initUserProfile()
    }
}
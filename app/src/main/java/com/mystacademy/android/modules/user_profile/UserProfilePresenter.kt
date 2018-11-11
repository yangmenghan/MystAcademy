package com.mystacademy.android.modules.user_profile

import com.apollographql.apollo.ApolloCall.Callback
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.mystacademy.android.UserProfileQuery.Data
import com.mystacademy.android.repositories.sharedPreferences.SharedPreferencesRepositoryImpl
import com.mystacademy.android.repositories.user.UserRepositoryImpl

class UserProfilePresenter(private val userProfileDependencies: UserProfileDependencies) :
    UserProfileContract.Presenter {

    override fun initUserProfile() {
        val callback = object : Callback<Data>() {
            override fun onFailure(e: ApolloException) {
                e.printStackTrace()
            }

            override fun onResponse(response: Response<Data>) {
                if (response.data()?.viewer() == null) {
                    onFailure(ApolloException("Null Response"))
                    return
                }
                handler.post {
                    displayUserProfile(response.data()!!.viewer()!!, view)
                }
            }

        }

        UserRepositoryImpl(SharedPreferencesRepositoryImpl.getInstance(activity.applicationContext!!)).getUserProfile(
            callback
        )
    }
}
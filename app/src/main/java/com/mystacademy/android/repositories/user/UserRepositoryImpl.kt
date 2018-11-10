package com.mystacademy.android.repositories.user

import com.apollographql.apollo.ApolloCall.Callback
import com.mystacademy.android.UserProfileQuery
import com.mystacademy.android.repositories.sharedPreferences.SharedPreferencesRepository
import com.mystacademy.android.utils.ApolloClientFactory

class UserRepositoryImpl(sharedPreferencesRepository: SharedPreferencesRepository) : UserRepository {
    private val client = ApolloClientFactory().getClient(sharedPreferencesRepository)

    override fun getUserProfile(callback: Callback<UserProfileQuery.Data>?) {
        client.query(UserProfileQuery()).enqueue(callback)
    }

}
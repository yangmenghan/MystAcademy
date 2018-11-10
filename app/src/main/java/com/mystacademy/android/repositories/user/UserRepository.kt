package com.mystacademy.android.repositories.user

import com.apollographql.apollo.ApolloCall.Callback
import com.mystacademy.android.UserProfileQuery

interface UserRepository {
    fun getUserProfile(callback: Callback<UserProfileQuery.Data>?)
}
package com.mystacademy.android.repositories.user

import com.apollographql.apollo.ApolloCall.Callback
import com.apollographql.apollo.ApolloClient
import com.mystacademy.android.UserProfileQuery

class UserRepositoryImpl(networkClient: ApolloClient) : UserRepository {
    private val client = networkClient

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(networkClient: ApolloClient): UserRepository {
            return instance ?: UserRepositoryImpl(networkClient).apply { instance = this }
        }
    }


    override fun getUserProfile(callback: Callback<UserProfileQuery.Data>?) {
        client.query(UserProfileQuery()).enqueue(callback)
    }

}
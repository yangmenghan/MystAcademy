package com.mystacademy.android.utils.injector

import com.apollographql.apollo.ApolloClient
import com.mystacademy.android.network.NetworkClientHolder
import com.mystacademy.android.repositories.sharedPreferences.SharedPreferencesRepository
import com.mystacademy.android.repositories.sharedPreferences.SharedPreferencesRepositoryImpl
import com.mystacademy.android.repositories.user.UserRepository
import com.mystacademy.android.repositories.user.UserRepositoryImpl

object InjectorImpl : Injector {

    override fun preferences(): SharedPreferencesRepository {
        return SharedPreferencesRepositoryImpl.getInstance()
    }

    override fun client(): ApolloClient {
        return NetworkClientHolder.getClient()
    }

    override fun users(): UserRepository {
        return UserRepositoryImpl.getInstance(client())
    }
}


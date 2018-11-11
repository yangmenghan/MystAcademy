package com.mystacademy.android.utils.injector

import com.apollographql.apollo.ApolloClient
import com.mystacademy.android.repositories.sharedPreferences.SharedPreferencesRepository
import com.mystacademy.android.repositories.user.UserRepository

interface Injector {
    fun preferences(): SharedPreferencesRepository
    fun client(): ApolloClient

    fun users(): UserRepository
}
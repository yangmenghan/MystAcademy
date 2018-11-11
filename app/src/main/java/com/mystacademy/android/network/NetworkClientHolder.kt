package com.mystacademy.android.network

import com.apollographql.apollo.ApolloClient
import com.mystacademy.android.repositories.sharedPreferences.SharedPreferencesRepository
import com.mystacademy.android.utils.InitializationException

object NetworkClientHolder {
    private var client: ApolloClient? = null

    fun init(sharedPreferencesRepository: SharedPreferencesRepository) {
        client = ApolloClientFactory().createClient(sharedPreferencesRepository)
    }

    fun getClient(): ApolloClient {
        if (client == null) throw InitializationException("Client holder not initialized")

        return client!!
    }
}
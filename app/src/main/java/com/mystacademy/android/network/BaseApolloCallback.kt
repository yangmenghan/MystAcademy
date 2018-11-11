package com.mystacademy.android.network

import com.apollographql.apollo.ApolloCall.Callback
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

class BaseApolloCallback<T>(val successCallback: () -> Unit) : Callback<T>() {
    override fun onFailure(e: ApolloException) {
        e.printStackTrace()
    }

    override fun onResponse(response: Response<T>) {
        if (response.hasErrors() || response.data() == null) {
            onFailure(ApolloException("Request Failed"))
            return
        } else {
            successCallback()
        }
    }
}
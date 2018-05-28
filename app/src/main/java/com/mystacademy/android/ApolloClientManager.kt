package com.mystacademy.android

import com.apollographql.apollo.ApolloClient
import com.mystacademy.android.secrets.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level


/**
 * Created by menghan on 30/03/2018.
 */

class ApolloClientManager {
  companion object {
    fun getClient(): ApolloClient {
      val headerInterceptor = Interceptor { chain ->
        val request =
          chain.request().newBuilder().addHeader(AppConstants.APOLLO_HEADER, AppConstants.APOLLO_HEADER_CONTENT).build()
        chain.proceed(request)
      }

//    val logInterceptor = OkLogInterceptor.builder().ignoreTimber(true).withAllLogData().build()
      val logging = HttpLoggingInterceptor()
      logging.level = Level.BODY
      val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).addInterceptor(headerInterceptor).build()
      return ApolloClient.builder().serverUrl(AppConstants.SERVER_URL).okHttpClient(okHttpClient).build()
    }

  }
}
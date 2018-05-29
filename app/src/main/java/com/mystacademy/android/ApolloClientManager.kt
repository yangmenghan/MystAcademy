package com.mystacademy.android

import com.apollographql.apollo.ApolloClient
import com.github.simonpercic.oklog3.OkLogInterceptor
import com.mystacademy.android.secrets.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import timber.log.Timber


/**
 * Created by menghan on 30/03/2018.
 */

class ApolloClientManager {
  companion object {
    fun getClient(): ApolloClient {
      // Header interceptor init
      val headerInterceptor = Interceptor { chain ->
        val request =
          chain.request().newBuilder().addHeader(AppConstants.APOLLO_HEADER, AppConstants.APOLLO_HEADER_CONTENT).build()
        chain.proceed(request)
      }

      // OkLog init
      val logInterceptor =
        OkLogInterceptor.builder().withRequestBody(true).withAllLogData()
          .build()

      // Standard http Log interceptor
      val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
        Timber.tag("OkHttp").d(it)
      })
      logging.level = Level.BODY

      // OkHttp client init
      val okHttpClient =
        OkHttpClient.Builder().addInterceptor(logInterceptor).addInterceptor(logging)
          .addInterceptor(headerInterceptor)
          .build()
      return ApolloClient.builder().serverUrl(AppConstants.SERVER_URL).okHttpClient(okHttpClient).build()
    }

  }
}
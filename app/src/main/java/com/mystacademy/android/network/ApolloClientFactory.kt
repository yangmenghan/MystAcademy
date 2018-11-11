package com.mystacademy.android.network

import com.apollographql.apollo.ApolloClient
import com.github.simonpercic.oklog3.OkLogInterceptor
import com.mystacademy.android.repositories.sharedPreferences.SharedPreferencesRepository
import com.mystacademy.android.secrets.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import timber.log.Timber

class ApolloClientFactory {
    fun createClient(sharedPreferencesRepository: SharedPreferencesRepository): ApolloClient {
        val headerInterceptor = getHeaderInterceptor(sharedPreferencesRepository)
        val logInterceptor = getLogInterceptor()
        val requestLogger = getRequestLogger()
        val okHttpClient = createClient(logInterceptor, requestLogger, headerInterceptor)

        return getApolloClient(okHttpClient)
    }


    private fun getLogInterceptor() = OkLogInterceptor.builder().withRequestBody(true).withAllLogData()
        .build()

    private fun getRequestLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.tag("OkHttp").d(it)
        }).apply { this.level = Level.BODY }
    }

    private fun getHeaderInterceptor(sharedPreferencesRepository: SharedPreferencesRepository): Interceptor {
        return Interceptor { chain ->
            val request =
                chain.request().newBuilder()
                    .addHeader(AppConstants.APOLLO_HEADER, AppConstants.APOLLO_HEADER_CONTENT)

            if (sharedPreferencesRepository.getToken() != null) {
                request.addHeader("Bearer", sharedPreferencesRepository.getToken()!!)
            }
            chain.proceed(request.build())
        }
    }

    private fun createClient(
        logInterceptor: OkLogInterceptor,
        logging: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient {
        return Builder().addInterceptor(headerInterceptor).addInterceptor(logInterceptor).addInterceptor(logging)
            .build()

    }

    private fun getApolloClient(okHttpClient: OkHttpClient) =
        ApolloClient.builder().serverUrl(AppConstants.SERVER_URL).okHttpClient(okHttpClient).build()


}
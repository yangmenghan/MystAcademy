package com.mystacademy.android.repositories.sharedPreferences

import android.content.Context
import com.mystacademy.android.utils.InitializationException

class SharedPreferencesRepositoryImpl private constructor(context: Context) : SharedPreferencesRepository {
    private val KEY_TOKEN = "key_token"
    private val KEY_PREFERENCES = "key_myst_academy"
    private val sharedPreferences = context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)

    companion object {
        private var instance: SharedPreferencesRepository? = null

        fun getInstance(): SharedPreferencesRepository {
            if (instance == null) throw InitializationException("Shared preferences repository not initialized")

            return instance!!
        }

        fun init(context: Context) {
            instance = SharedPreferencesRepositoryImpl(context)
        }
    }


    override fun setToken(token: String?) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }
}
package com.mystacademy.android.repositories.sharedPreferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesRepositoryImpl private constructor(context: Context) : SharedPreferencesRepository {
    private val KEY_TOKEN = "key_token"
    private val KEY_PREFERENCES = "key_myst_academy"
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
    }

    companion object {
        private var instance: SharedPreferencesRepository? = null
        fun getInstance(context: Context): SharedPreferencesRepository {
            if (instance == null) {
                instance = SharedPreferencesRepositoryImpl(context)
            }
            return instance!!
        }
    }

    override fun setToken(token: String?) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }
}
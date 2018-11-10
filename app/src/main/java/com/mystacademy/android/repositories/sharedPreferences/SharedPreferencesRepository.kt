package com.mystacademy.android.repositories.sharedPreferences

interface SharedPreferencesRepository {
    fun setToken(token: String?)
    fun getToken(): String?
}
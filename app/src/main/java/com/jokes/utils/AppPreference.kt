package com.criclivline.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit


class AppPreference(context: Context) {

    companion object {


        private const val KEY_ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val KEY_IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN"
        private const val KEY_IS_AMOUNT_ADDED = "IS_AMOUNT_ADDED"


        private const val PREFS_FILENAME = "jokes_app_prefs"


    }

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)


    var accessToken: String
        get() = sharedPrefs.getString(KEY_ACCESS_TOKEN, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_ACCESS_TOKEN, value) }


    var isUserLoggedIn: Boolean
        get() = sharedPrefs.getBoolean(KEY_IS_USER_LOGGED_IN, false) ?: false
        set(value) = sharedPrefs.edit { putBoolean(KEY_IS_USER_LOGGED_IN, value) }

    var isAmountAdded: Boolean
        get() = sharedPrefs.getBoolean(KEY_IS_AMOUNT_ADDED, false) ?: false
        set(value) = sharedPrefs.edit { putBoolean(KEY_IS_AMOUNT_ADDED, value) }



    fun clearAllPref() {
        sharedPrefs.edit().clear().apply()

    }

}



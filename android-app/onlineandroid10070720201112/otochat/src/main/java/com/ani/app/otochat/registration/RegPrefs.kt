package com.ani.app.otochat.registration

import android.content.SharedPreferences

class RegPrefs {
    companion object {
        fun markRegistered(prefs : SharedPreferences) =
            prefs.edit().putBoolean(KEY_IS_REGISTERED, true).apply()

        fun isRegistered(prefs: SharedPreferences) =
             prefs.getBoolean(KEY_IS_REGISTERED, false)

        fun markLoggedIn( prefs : SharedPreferences) =
            prefs.edit().putBoolean(KEY_IS_LOGGED_IN, true).apply()

        fun isLoggedIn(prefs: SharedPreferences) = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

        private const val KEY_IS_REGISTERED = "isRegistered"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }
}
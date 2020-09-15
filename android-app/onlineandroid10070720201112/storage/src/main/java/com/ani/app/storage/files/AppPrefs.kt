package com.ani.app.storage.files

import android.app.Activity
import android.content.Context
import android.util.Log

class AppPrefs(
    private val context : Activity
) {

    fun writeData() {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("key_boolean", true)
        editor.putString("key_string", "its string")
        editor.putInt("key_int", 10)
        editor.apply()
    }


    fun readData() {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        Log.i("@ani", "Boolean - ${prefs.getBoolean("key_boolean", false)}")
        Log.i("@ani", "String - ${prefs.getString("key_string", null)}")
        Log.i("@ani", "Int - ${prefs.getInt("key_int", -1)}")
    }

    fun writeActSpecPrefs() {
        val prefs = context.getPreferences(Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("key_boolean", true)
        editor.putString("key_string", "its string")
        editor.putInt("key_int", 10)
        editor.apply()
    }

    fun readActSpecPrefs() {
        val prefs = context.getPreferences(Context.MODE_PRIVATE)
        Log.i("@ani", "Boolean - ${prefs.getBoolean("key_boolean", false)}")
        Log.i("@ani", "String - ${prefs.getString("key_string", null)}")
        Log.i("@ani", "Int - ${prefs.getInt("key_int", -1)}")
    }
}
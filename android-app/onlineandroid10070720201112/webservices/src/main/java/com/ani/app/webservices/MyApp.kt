package com.ani.app.webservices

import android.app.Application
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MyApp : Application() {

    val q: RequestQueue by lazy {
        Volley.newRequestQueue(this)
    }

    val gson: Gson by lazy {
        Gson()
    }
}
package com.ani.app.otochat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ani.app.otochat.registration.RegistrationActivity

class MainActivity : AppCompatActivity() {

    var isRegistered = true
    var isLoggedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Splash Screen
         * */
        Handler().postDelayed(Runnable {
            startRegistrationActivity()
        }, 3000)
    }

    private fun startRegistrationActivity() {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }
}

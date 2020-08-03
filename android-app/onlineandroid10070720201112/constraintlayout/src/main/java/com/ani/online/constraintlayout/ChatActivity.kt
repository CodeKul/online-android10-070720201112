package com.ani.online.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

       val chatBundle = intent?.extras
        val from = chatBundle?.getString("from")

        supportActionBar?.title = from ?: "ConstraintLayout"
    }
}
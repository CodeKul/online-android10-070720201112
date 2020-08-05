package com.ani.online.constraintlayout

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

       val chatBundle = intent?.extras
        val from = chatBundle?.getString(MainActivity.KEY_FROM)

        supportActionBar?.title = from ?: "ConstraintLayout"

        btSend.setOnClickListener {
            txtHi.text = etChat.text
        }

        txtHi.setOnClickListener {
            val dtInt = Intent()
            dtInt.putExtra(KEY_DATA_CHAT, txtHi.text.toString())
            setResult(Activity.RESULT_OK, dtInt)
            finish()
        }
    }

    companion object {
        const val KEY_DATA_CHAT = "data_chat"
    }
}
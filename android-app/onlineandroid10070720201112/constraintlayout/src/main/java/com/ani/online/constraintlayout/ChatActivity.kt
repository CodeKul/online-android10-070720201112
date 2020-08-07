package com.ani.online.constraintlayout

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()

        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()

        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()

        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
        super.onPause()
    }

    override fun onStop() {
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
        super.onStop()
    }

    override fun onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    companion object {
        const val KEY_DATA_CHAT = "data_chat"
    }
}
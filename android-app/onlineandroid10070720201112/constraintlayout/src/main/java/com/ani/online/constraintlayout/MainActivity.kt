package com.ani.online.constraintlayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutItem.setOnClickListener {

            val chatBundle = Bundle()
            chatBundle.putString("from", txtNm.text.toString())

            val cls: Class<ChatActivity> = ChatActivity::class.java
            val chatIntent = Intent(this@MainActivity, cls)
            chatIntent.putExtras(chatBundle)
            startActivity(chatIntent)
        }
    }
}

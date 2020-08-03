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
            val cls: Class<ChatActivity> = ChatActivity::class.java
            val chatIntent = Intent(this@MainActivity, cls)
            startActivity(chatIntent)
        }
    }
}

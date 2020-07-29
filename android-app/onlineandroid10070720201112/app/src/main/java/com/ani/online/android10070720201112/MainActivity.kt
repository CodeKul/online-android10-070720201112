package com.ani.online.android10070720201112

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btLgn.setOnClickListener {
            it?.let {
                val etUsNm = findViewById<EditText>(R.id.etUsNm)
                Log.i("@ani", "Login Clicked")
                Log.i("@ani", "User Name - ${etUsNm.text}")
                Log.i("@ani", "Password - ${etPass.text}")
            }
        }
    }
}
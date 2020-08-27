package com.ani.online.broadcastreceivers

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val samp = Intent()
        samp.action = Intent.ACTION_CALL
        samp.action = Intent.ACTION_DIAL
            samp.action = Intent.ACTION_VIEW
            samp.setData(Uri.parse("https://google.com"))
            startActivity(samp)
    }
}

package com.ani.online.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment()
    }

    fun loadFragment() {
        val frag = ColorBarsFragment()
        val txn = supportFragmentManager.beginTransaction()
        txn.replace(R.id.frameBar, frag)
        txn.commit()
    }
}

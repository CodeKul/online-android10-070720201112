package com.ani.online.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout

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

    fun emitColorChange(red : Int, green : Int, blue : Int) {
//        Log.i("@ani", "Red $red, Green $green, Blue $blue")
        val displayFragment = supportFragmentManager.findFragmentById(R.id.fragment2) as DisplayFragment

        displayFragment.setNewColor(red, green, blue)
    }
}

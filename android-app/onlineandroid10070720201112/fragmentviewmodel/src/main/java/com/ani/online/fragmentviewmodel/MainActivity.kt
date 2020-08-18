package com.ani.online.fragmentviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ani.online.fragmentviewmodel.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            val frag = MainFragment.newInstance("hello")
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .commitNow()
        }

        Class.forName("").newInstance()
    }
}

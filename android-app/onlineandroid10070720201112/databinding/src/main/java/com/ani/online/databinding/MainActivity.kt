package com.ani.online.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ani.online.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1. MVC
    // 2. MVP
    // 3. MVVM

    private val viewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)


    }
}

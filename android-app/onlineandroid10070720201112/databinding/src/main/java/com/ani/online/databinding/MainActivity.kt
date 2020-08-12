package com.ani.online.databinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ani.online.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1. MVC
    // 2. MVP
    // 3. MVVM

    private val TAG = MainActivity::class.java.canonicalName
    private lateinit var os : String
    private val viewModel by lazy {
        ViewModelProvider(this)
            .get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

//        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
//            this, R.layout.activity_main)
//        binding.lifecycleOwner = this
//        binding.lgnVm = viewModel

        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main)?.apply {
            lifecycleOwner = this@MainActivity
            lgnVm = viewModel
        }

        viewModel.lgnSts.observe(this, Observer<Boolean> {
                sts -> if(sts) startChatActivity()
        })
    }

    private fun startChatActivity() {
        startActivity(Intent(this, ChatActivity::class.java))
    }
}

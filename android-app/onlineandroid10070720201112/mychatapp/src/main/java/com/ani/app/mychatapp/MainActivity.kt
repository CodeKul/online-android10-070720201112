package com.ani.app.mychatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ani.app.mychatapp.contact.ContactUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val conUt = ContactUtil(this)
        val contacts = conUt.fetchContacts()
    }
}

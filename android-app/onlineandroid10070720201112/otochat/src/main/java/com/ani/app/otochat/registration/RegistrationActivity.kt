package com.ani.app.otochat.registration

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ani.app.otochat.R
import com.ani.app.otochat.friends.FriendListActivity

class RegistrationActivity : AppCompatActivity() {

    private val prefs: SharedPreferences by lazy {
        getSharedPreferences(RegPrefs.PREFS_NAME, MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        if (RegPrefs.isRegistered(prefs)) {
            if (RegPrefs.isLoggedIn(prefs)) {
                startFriendsActivity()
            }
            else {
                loadFragment(LoginFragment.newInstance("",""))
            }
        } else {
            loadFragment(RegistrationFragment.newInstance("", ""))
        }
    }

    fun loadFragment(frag: Fragment) {
        // you need to complete the code
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.regFrm, frag)
            .commit()
    }

    fun startFriendsActivity() {
        startActivity(Intent(this, FriendListActivity::class.java))
    }

    fun appPrefs() = getSharedPreferences(RegPrefs.PREFS_NAME, MODE_PRIVATE)
}
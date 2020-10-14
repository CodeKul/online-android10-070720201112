package com.ani.app.otochat.friends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.ani.app.otochat.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_friend_list.*

class FriendListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_list)

        fabAdd.setOnClickListener {
            addFriend()
        }
    }

    fun addFriend() {
        val builder = MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialog_rounded)
        val alert = builder.create()

        val view = LayoutInflater
            .from(this)
            .inflate(R.layout.dialog_add_friend, null)
        alert.setView(view)
        alert.show()
    }
}
package com.ani.app.otochat.chat

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.intentservice.chatui.models.ChatMessage
import com.ani.app.otochat.R
import com.ani.app.otochat.registration.RegPrefs
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    private lateinit var fbd: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference

    private val prefs: SharedPreferences by lazy {
        getSharedPreferences(
            RegPrefs.PREFS_NAME,
            MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        initFirebase()

        val chatWith = intent.extras?.getString("chatWith") ?: "guest"

        chatView.addMessage(
            ChatMessage(
                "Message received",
                System.currentTimeMillis(),
                ChatMessage.Type.RECEIVED
            )
        )

        chatView.addMessage(
            ChatMessage(
                "Message SENT",
                System.currentTimeMillis(),
                ChatMessage.Type.SENT
            )
        )

        chatView.setOnSentMessageListener { msg ->
           msg?.let {
               chatView.addMessage(it)
           }
            true
        }
    }

    private fun initFirebase() {
        fbd = Firebase.database
        dbRef = fbd.getReference(RegPrefs.myId(prefs))
    }
}
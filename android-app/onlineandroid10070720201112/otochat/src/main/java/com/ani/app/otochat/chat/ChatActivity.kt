package com.ani.app.otochat.chat

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import co.intentservice.chatui.models.ChatMessage
import com.ani.app.otochat.R
import com.ani.app.otochat.registration.RegPrefs
import com.google.firebase.database.*
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

        val chatWith = intent.extras?.getString("chatWith") ?: "guest"

        initFirebase()

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
               val sentMsg : HashMap<String, String> = HashMap()
               sentMsg.put("msg", it.message)
               sentMsg.put("from", RegPrefs.myId(prefs))
               dbRef.parent?.child(chatWith)?.child("chats")?.setValue(sentMsg)
           }
            true
        }
        dbRef.child("chats").addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                Log.i("@ani", "Changed Value ===> ${snapshot.value}")
                val msg = snapshot.value as HashMap<String, String>
                if(msg.get("from") != RegPrefs.myId(prefs)) {
                    chatView.addMessage(
                        ChatMessage(
                            msg.get("msg"),
                            System.currentTimeMillis(),
                            ChatMessage.Type.RECEIVED
                        )
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun initFirebase() {
        fbd = Firebase.database
        dbRef = fbd.getReference(RegPrefs.myId(prefs))
    }
}
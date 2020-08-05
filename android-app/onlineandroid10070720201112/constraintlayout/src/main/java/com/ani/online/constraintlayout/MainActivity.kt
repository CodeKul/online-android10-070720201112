package com.ani.online.constraintlayout

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutItem.setOnClickListener {

            val chatBundle = Bundle()
            chatBundle.putString(KEY_FROM, txtNm.text.toString())

            val cls: Class<ChatActivity> = ChatActivity::class.java
            val chatIntent = Intent(this@MainActivity, cls)
            chatIntent.putExtras(chatBundle)
//            startActivity(chatIntent)
            startActivityForResult(chatIntent, REQ_CHAT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQ_CHAT) {
            if(resultCode == Activity.RESULT_OK) {
                msg.text = data?.getStringExtra(ChatActivity.KEY_DATA_CHAT)
            }
        }
    }

    companion object {
        const val KEY_FROM = "from"
        const val REQ_CHAT = 9879
    }
}

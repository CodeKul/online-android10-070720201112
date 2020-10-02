package com.ani.app.fcm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFcm()

        btnSnd.setOnClickListener {

        }
    }

    private fun initFcm() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.i("@ani", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                val token = task.result?.token
                token?.let {
                    txtTkn.setText(it)
                }

                // your web service to post the token

                Log.i("@ani", "Token is $token")
            })
    }
}

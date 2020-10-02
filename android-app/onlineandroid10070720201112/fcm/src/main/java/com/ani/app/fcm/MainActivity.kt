package com.ani.app.fcm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFcm()
    }

    private fun initFcm() {

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.i("@ani", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                val token = task.result?.token

                // your web service to post the token

                Log.i("@ani", "Token is $token")
            })
    }
}

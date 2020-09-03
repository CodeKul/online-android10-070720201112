package com.ani.business.services

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appLcInt = Intent(this, AppLocationService::class.java)
        startService(appLcInt)

        btnSt.setOnClickListener {
            bind(appLcInt)
        }

        Log.i("@ani", "cntVal")
    }

    private fun bind(appLcInt : Intent) {
        bindService(
            appLcInt,
            object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    Log.i("@ani", "Connected to AppLocationService")
                    val lb = service as AppLocationService.LocalBinder
                    val appLocationService = lb.getService()
                    appLocationService.setJustLocationChangedListener (object : JustLocationChangedListener {
                        override fun justLocationChanged(cv: Int) {
                            Log.i("@ani" , "CNT VAL -> $cv")
                        }
                    })

                    Log.i("@ani", "Count it ${appLocationService.cntVal}")
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                    Log.i("@ani", "Disconnected from AppLocationService")
                }
            },
            BIND_AUTO_CREATE)
    }
}

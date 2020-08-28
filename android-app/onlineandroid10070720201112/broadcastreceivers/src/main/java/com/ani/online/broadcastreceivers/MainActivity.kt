package com.ani.online.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val br = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.i("@ani", "${intent?.action}")
            if(intent?.action == Intent.ACTION_POWER_CONNECTED) {
                imgBat.setImageResource(R.drawable.ic_baseline_battery_charging_full_24)
                val anim = AnimationUtils.loadAnimation(context, R.anim.rotate )
                imgBat.startAnimation(anim)
            }
            if(intent?.action == Intent.ACTION_POWER_DISCONNECTED) {
                imgBat.setImageResource(R.drawable.ic_baseline_battery_std_24)
                val anim = AnimationUtils.loadAnimation(context, R.anim.rotate )
                imgBat.startAnimation(anim)
            }
         }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgBat.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.rotate )
            imgBat.startAnimation(anim)
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        registerReceiver(br, filter)
    }

    override fun onStop() {
        unregisterReceiver(br)
        super.onStop()
    }
}

package com.ani.business.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class DemoService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.i("@ani", "On Create")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("@ani", "On Start Command")
        Thread {
            for (i in 0..1000) {
                Thread.sleep(1000)
                Log.i("@ani", "---> $i")
            }
        }.start()

        notification()

        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onDestroy() {
        Log.i("@ani", "On Destroy")
        super.onDestroy()
    }

    private fun notification() {
        val channelId = createNotificationChannel("123", "ani")
        val builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Title")
            .setContentText("content")
            .setSmallIcon(R.drawable.ic_baseline_battery_std_24)
            .setTicker("Ticker")
        startForeground(1346, builder.build())
    }

    private fun createNotificationChannel(channelId: String, channelName: String): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val chan = NotificationChannel(
                channelId,
                channelName, NotificationManager.IMPORTANCE_NONE
            )
            chan.lightColor = Color.BLUE
            chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            service.createNotificationChannel(chan)
            return channelId
        }
        return ""
    }
}

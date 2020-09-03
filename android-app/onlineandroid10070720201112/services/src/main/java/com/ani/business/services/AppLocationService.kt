package com.ani.business.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class AppLocationService : Service() {

    var cntVal = 0

    private var mListener : JustLocationChangedListener? = null

    override fun onBind(intent: Intent): IBinder = LocalBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread {
            for ( i in 0 .. 1000) {
                Thread.sleep(1000)
//                Log.i("@ani", "$i")
                cntVal = i
                mListener?.justLocationChanged(cntVal)
            }
        }.start()

        notification()

        return START_STICKY
    }

    inner class LocalBinder : Binder() {
        fun getService() : AppLocationService = this@AppLocationService
    }

    private fun notification() {
        val channelId = createNotificationChannel("123", "ani")
        val builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Location")
            .setContentText("need your location")
            .setSmallIcon(R.drawable.ic_baseline_gps_fixed_24)
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

    fun setJustLocationChangedListener(listener : JustLocationChangedListener) {
       mListener = listener
    }
}

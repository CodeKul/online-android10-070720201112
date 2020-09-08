package com.ani.business.services

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*

class AppLocationService : Service() {

    var cntVal = 0

    private lateinit var client: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest

    //    private var mListener : JustLocationChangedListener? = null // java way
    var cbFn: ((location: Location?) -> Unit)? = null // kotlins way

    override fun onCreate() {
        super.onCreate()

        client = LocationServices.getFusedLocationProviderClient(this)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult?) {
                super.onLocationResult(result)
                //mListener?.justLocationChanged(result?.lastLocation)
                cbFn?.invoke(result?.lastLocation)
            }
        }

        locationRequest = LocationRequest()
        locationRequest.interval = 1000;
        locationRequest.fastestInterval = 1500;
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY;
    }

    override fun onBind(intent: Intent): IBinder = LocalBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("@ani", "Service Started")

        if( (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)) {
            Log.i("@ani", "Requesting location updates")
            client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
            notification()
        }

        return START_STICKY
    }

    inner class LocalBinder : Binder() {
        fun getService(): AppLocationService = this@AppLocationService
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

//    fun setJustLocationChangedListener(listener : JustLocationChangedListener) {
//       mListener = listener
//    }
}

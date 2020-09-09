package com.ani.business.services

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var appLcInt : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appLcInt = Intent(this, AppLocationService::class.java)
        if(
            (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {
            startService(appLcInt)
        } else {
            val builder = AlertDialog.Builder(this)
                .setTitle("Permission")
                .setMessage("Need your permission for accessing location :)")
                .setPositiveButton("Grant") { di, wh ->
                    requestPermissions(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ),
                        1345
                    )
                }
                .setNegativeButton("Deny") { di, wh -> }
            val dialog = builder.create()
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }

        btnSt.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        Log.i("@ani", "cntVal")
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 1345) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED ) {
                startService(appLcInt)
            }
        }
    }
}

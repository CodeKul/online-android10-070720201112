package com.ani.business.services

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_main.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        bind(Intent(this, AppLocationService::class.java))

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
//        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        mMap.setOnMapClickListener {
            val clkPos = it
            mMap.addMarker(
                MarkerOptions().position(clkPos).title("${it.latitude}, ${it.longitude}")
            )
        }

        val pune = LatLng(18.13, 73.52)
        val delhi = LatLng(28.70, 77.10)

        mMap.addCircle(
            CircleOptions()
                .center(pune)
                .radius(10000.0)
                .strokeColor(Color.BLUE)
                .fillColor(Color.RED)
        )

        mMap.addPolyline(
            PolylineOptions().add(pune,delhi)
                .color(Color.BLUE)
                .width(5.9F)
        )

        mMap.addMarker(
            MarkerOptions().position(pune).title("Marker in Sydney")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pune))
    }

    private fun bind(appLcInt : Intent) {
        bindService(
            appLcInt,
            object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    Log.i("@ani", "Connected to AppLocationService")
                    val lb = service as AppLocationService.LocalBinder
                    val appLocationService = lb.getService()
//                    appLocationService.setJustLocationChangedListener (object : JustLocationChangedListener {
//                        override fun justLocationChanged(loc : Location?) {
//                            btnSt.text = "${loc?.latitude}, ${loc?.longitude}"
//                        }
//                    })
                    appLocationService.cbFn =  {
                        it?.let {
                            mMap.addMarker(
                                MarkerOptions()
                                    .position(
                                        LatLng(it.latitude, it.longitude)
                                    )
                            )
                        }
                    }

                    Log.i("@ani", "Count it ${appLocationService.cntVal}")
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                    Log.i("@ani", "Disconnected from AppLocationService")
                }
            },
            BIND_AUTO_CREATE)
    }
}
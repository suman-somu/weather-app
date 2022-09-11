package com.example.weatherapp

import android.Manifest
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                } else -> {
                // No location access granted.
            }
            }
        }
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))



        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                // Got last known location. In some rare situations this can be null.

                var longitude = location?.longitude
                var latitude = location?.latitude

                Log.d("RIYA ","$longitude $latitude")


                var lngtd = findViewById<View>(R.id.temp_max) as TextView
                var lttd = findViewById<View>(R.id.temp_min) as TextView

                var l1 = longitude?.toBigDecimal()?.toPlainString()
                lngtd.setText("longitude = $l1")
                lngtd.invalidate()
                lngtd.requestLayout()

                var l2 = latitude?.toBigDecimal()?.toPlainString()
                lttd.setText("lattitude = $l2")
                lttd.invalidate()
                lttd.requestLayout()


                //lttd.setText(latitude)

            }


    }

}


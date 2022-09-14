package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lngtd = findViewById<View>(R.id.temp_max) as TextView
        var lttd = findViewById<View>(R.id.temp_min) as TextView

        lngtd.setText("longitude ")
        lngtd.invalidate()
        lngtd.requestLayout()

        lttd.setText("lattitude ")
        lttd.invalidate()
        lttd.requestLayout()



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


        var getweather = findViewById<View>(R.id.getweather) as Button

        getweather.setOnClickListener {
            val text = "button cliked!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()





            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@setOnClickListener
            }
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    // Got last known location. In some rare situations this can be null.

                    var longitude = location?.longitude
                    var latitude = location?.latitude

                    Log.d("RIYA ","$longitude $latitude")


                    var lngtd = findViewById<View>(R.id.temp_max) as TextView
                    var lttd = findViewById<View>(R.id.temp_min) as TextView

                    //var l1 = longitude?.toBigDecimal()?.toPlainString()
                    var l1 = longitude?.toString()
                    lngtd.setText("longitude = $l1")
                    lngtd.invalidate()
                    lngtd.requestLayout()

                    var l2 = latitude?.toString()
                    lttd.setText("lattitude = $l2")
                    lttd.invalidate()
                    lttd.requestLayout()


                }

        }
    }
}


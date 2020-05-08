package com.example.rideapp

import android.widget.EditText
import android.widget.TextView

interface Interface {
    var placename: String

    fun placeIntent(requestCode: Int, locationfrom: EditText)
    fun success()
    fun onStarted()
    fun Failure()
    fun CurrentLocation(currentlocation: TextView)
}
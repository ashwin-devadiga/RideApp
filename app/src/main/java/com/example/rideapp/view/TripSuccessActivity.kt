package com.example.rideapp.view

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.rideapp.R

class TripSuccessActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createtrip_success)


        val actionbar: ActionBar? =supportActionBar

        actionbar?.elevation = 0F
        actionbar?.setHomeAsUpIndicator(getDrawable(R.drawable.ic_arrow_back_black_24dp))
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


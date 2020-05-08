package com.example.rideapp.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rideapp.R
import com.example.rideapp.adapterClass.TripsummaryAdapter
import com.example.rideapp.viewModel.CreateTripViewModel
import kotlinx.android.synthetic.main.createtrip_layout.*
import kotlinx.android.synthetic.main.createtrip_layout.recyclerView
import kotlinx.android.synthetic.main.trip_summary.*
import kotlinx.android.synthetic.main.trip_summary.inviteRiders

class TripsummaryActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trip_summary)

         val actionBar: ActionBar? = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F99C56")))
        actionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val customAdapter = TripsummaryAdapter()
        recyclerView.adapter = customAdapter


        //inviteRiders.setText("$contacts")

        createbutton.setOnClickListener {
            startActivity(Intent(this, TripSuccessActivity::class.java))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.edit, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
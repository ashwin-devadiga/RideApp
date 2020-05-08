package com.example.rideapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.rideapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)



        val actionBar = supportActionBar
        actionBar?.hide()

      Handler().postDelayed({startActivity(Intent(this, AppTourActivity::class.java))
        finish() }
        , 3000)


    }
}

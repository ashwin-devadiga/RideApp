package com.example.rideapp.VIEW

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rideapp.R
import com.example.rideapp.view.CreateTripActivity
import kotlinx.android.synthetic.main.apptour_layout3.*



class FragmentThree: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater!!.inflate(R.layout.apptour_layout3, container, false)
        return view




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register.setOnClickListener {
            startActivity(Intent(activity, CreateTripActivity::class.java))
        }
    }

}

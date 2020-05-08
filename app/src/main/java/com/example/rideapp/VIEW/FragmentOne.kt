package com.example.rideapp.VIEW

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rideapp.R

class FragmentOne: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view: View = inflater.inflate(R.layout.apptour_layout1, container, false)
        return view
    }
}
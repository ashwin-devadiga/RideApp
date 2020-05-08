package com.example.rideapp.adapterClass

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.rideapp.VIEW.FragmentOne
import com.example.rideapp.VIEW.FragmentTwo
import com.example.rideapp.VIEW.FragmentThree

class MyAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return FragmentOne()

            1 -> return FragmentTwo()

            2 -> return FragmentThree()

            else -> return FragmentOne()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}
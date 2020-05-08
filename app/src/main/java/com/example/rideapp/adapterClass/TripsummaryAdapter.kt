package com.example.rideapp.adapterClass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rideapp.R
import kotlinx.android.synthetic.main.mileston_cardview.view.*

class TripsummaryAdapter:  RecyclerView.Adapter<TripsummaryAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.mileston_cardview, parent, false)
        return ViewHolder(v)
        }

    override fun getItemCount(): Int {
        return  3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position == 0)
        {
            holder.itemView.line2.visibility = View.GONE
        }
        else if(position == 2)
        {
            holder.itemView.line3.visibility = View.GONE
        }
    }

}
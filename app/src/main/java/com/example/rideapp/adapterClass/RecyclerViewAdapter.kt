package com.example.rideapp.adapterClass


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rideapp.Interface
import com.example.rideapp.R

import com.example.rideapp.data.Model


class RecyclerViewAdapter(val modelList: ArrayList<Model>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    var listener: Interface? = null


    inner class ViewHolder(itemView: View, val Close: ImageView = itemView.findViewById(R.id.close)): RecyclerView.ViewHolder(itemView) {

        val locationfrom: EditText = itemView.findViewById(R.id.sourceCardview)
        val locationTo: EditText = itemView.findViewById(R.id.destinationCardview)
        val currentlocation: TextView = itemView.findViewById(R.id.currentLocationTextView)
        var string: String = ""

        //remove
        init {
            Close.setOnClickListener(remove())
        }

        fun remove(): (View) -> Unit =
            {
                layoutPosition.also { currentPosition ->
                    modelList.removeAt(currentPosition)
                    notifyDataSetChanged()
                }

            }

        fun bindItems(model: Model) {
            val milestoneTextView = itemView.findViewById<TextView>(R.id.milestoneTextView)
            milestoneTextView.setText(model.milestone)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
      return modelList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(modelList[position])
        listener?.CurrentLocation(holder.currentlocation)


        holder.locationTo.setOnClickListener {
            listener?.placeIntent(100, holder.locationTo)
        }

        holder.locationfrom.setOnClickListener {
            listener?.placeIntent(100, holder.locationfrom)
        }

    }





}



package com.example.rideapp.adapterClass

import android.content.Intent
import android.database.CursorJoiner
import android.provider.ContactsContract.Contacts
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rideapp.R
import com.example.rideapp.data.Data
import com.example.rideapp.data.Model

class ContactrvAdapter(var contact: ArrayList<Data>): RecyclerView.Adapter<ContactrvAdapter.ViewHolder>() {
    var inviteList: ArrayList<Data> = ArrayList<Data>()
  inner  class ViewHolder(itemView: View, val textView: TextView = itemView.findViewById(R.id.contactName)): RecyclerView.ViewHolder(itemView)
    {
        val select: CheckBox  = itemView.findViewById(R.id.select)
       // val close: ImageView = itemView.findViewById(R.id.close)
        init {
            select.setOnClickListener(addinvitelist())
        }

         fun  addinvitelist(): (View) -> Unit = {
             layoutPosition.also { currentPosition ->  inviteList.add(contact.get(currentPosition)) }
         }


        fun bindItems(data: Data) {
            textView.text = data.name

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.contactlist_layout, parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
       return contact.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(contact[position])
       // holder.select.setOnClickListener {

       // }


    }

    fun filterList(filteredList: ArrayList<Data>) {
        contact = filteredList
        notifyDataSetChanged()

    }


}
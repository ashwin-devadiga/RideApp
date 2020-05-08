package com.example.rideapp.view

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rideapp.Interface
import com.example.rideapp.R
import com.example.rideapp.viewModel.CreateTripViewModel
import com.example.rideapp.adapterClass.RecyclerViewAdapter
import com.example.rideapp.data.Model
import com.example.rideapp.databinding.CreatetripLayoutBinding
import com.example.rideapp.getCurrentLocation
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.createtrip_layout.*
import java.util.*
import kotlin.collections.ArrayList



class CreateTripActivity : AppCompatActivity(), Interface {

    lateinit var recyclerView: RecyclerView
    lateinit var editText2: EditText
    lateinit var placeEditText: EditText
    var string: String = android.Manifest.permission.ACCESS_FINE_LOCATION
    override var placename: String = ""
    var i= 0
    var models = ArrayList<Model>()
    var contact: ArrayList<String>? = null
    lateinit var mDatelistner: DatePickerDialog.OnDateSetListener
    lateinit var mtimelistener: TimePickerDialog.OnTimeSetListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: CreatetripLayoutBinding =  DataBindingUtil.setContentView(this, R.layout.createtrip_layout)
        val viewModel: CreateTripViewModel? = ViewModelProviders.of(this).get(CreateTripViewModel::class.java)
        binding.viewmodel  = viewModel

        viewModel?.listener = this


        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F99C56")))
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title  = "Create a Trip"

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.visibility =android.view.View.GONE

        //current location
        getCurrentLocation(currentlocation)

        sourceEditText.setOnClickListener {
            placeIntent(100, sourceEditText)
        }
        destinationEditText.setOnClickListener {
            placeIntent(101, destinationEditText)

        }


            //date
            startdateEditText.setOnClickListener {
                editText2 = findViewById(R.id.startdateEditText)
                calendar()
            }

            enddateEditText.setOnClickListener {
                editText2 = findViewById(R.id.enddateEditText)
                calendar()
            }
            //time

            startTimeEditText.setOnClickListener {
                val cal: Calendar = Calendar.getInstance()
                val hour = cal.get(Calendar.HOUR_OF_DAY)
                val min = cal.get(Calendar.MINUTE)
                val dialog: TimePickerDialog =
                    TimePickerDialog(this, mtimelistener, hour, min, false)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
                dialog.show()
            }

            mDatelistner = object : DatePickerDialog.OnDateSetListener {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onDateSet(
                    view: DatePicker, year: Int, month: Int, day: Int
                ) {
                    val days = DecimalFormat("00").format(day)
                    val months = DecimalFormat("00").format(month)
                    val date = "$days-$months-$year"
                    editText2.text = Editable.Factory.getInstance().newEditable(date)

                }

            }
            mtimelistener = object : TimePickerDialog.OnTimeSetListener {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    val am_pm: String
                    val hour: Int
                    if (hourOfDay < 12) {
                        am_pm = "AM"
                        if (hourOfDay == 0) {
                            hour = 12
                        } else
                            hour = hourOfDay
                    } else {
                        am_pm = "PM"
                        if (hourOfDay == 12) {
                            hour = 12
                        } else {
                            hour = hourOfDay - 12
                        }
                    }
                    val hours = DecimalFormat("00").format(hour)
                    val minutes = DecimalFormat("00").format(minute)
                    val time = "$hours : $minutes $am_pm"
                    startTimeEditText.text = Editable.Factory.getInstance().newEditable(time)
                }

            }
            //contact
            inviteRiders.setOnClickListener {
                startActivityForResult(Intent(this, ContactsActivity::class.java), 1000)
            }


            //milestone
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val customAdapter: RecyclerViewAdapter = RecyclerViewAdapter(models)
        recyclerView.adapter = customAdapter

            milestone.setOnClickListener {

                customAdapter.listener = this
                val string = sourceEditText.text.toString()
                val string1 = destinationEditText.text.toString()
                if (string != "" && string1 != "") {
                    i = models.size
                    models.add(Model("Milestone ${i + 1}"))

                    recyclerView.visibility = android.view.View.VISIBLE
                    customAdapter.notifyItemInserted(models.size)
                } else
                    Toast.makeText(
                    this,
                    " Please enter Trip starting and ending place",
                    Toast.LENGTH_SHORT
                ).show()
                done.setBackgroundResource(R.drawable.tripbutton)

            }
    }


    override fun placeIntent(requestCode: Int, locationText: EditText) {

        placeEditText = locationText
        var fieldlist: List<Place.Field> =
            Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME)

        val intent: Intent =
            Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fieldlist)
                .setCountry("IN").build(this)
        startActivityForResult(intent, requestCode)
    }



      fun calendar() {
            val cal: Calendar = Calendar.getInstance()
            val year: Int = cal.get(Calendar.YEAR)
            val month: Int = cal.get(Calendar.MONTH)
            val day: Int = cal.get(Calendar.DAY_OF_MONTH)
            val dialog: DatePickerDialog =
                DatePickerDialog(
                    this,
                    android.R.style.Theme_Material_Dialog_MinWidth,
                    mDatelistner,
                    year,
                    month,
                    day
                )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }

        //autocomplete result
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            //contact
            if (requestCode == 1000 ) {

                contact = data!!.getStringArrayListExtra("Data")
                inviteRiders.setText("$contact")

            }
            else if (resultCode == Activity.RESULT_OK){
                val place = Autocomplete.getPlaceFromIntent(data!!)
                if (requestCode == 100) {
                    placeEditText.text = Editable.Factory.getInstance().newEditable(place.name)

                } else if (requestCode == 101) {
                    placeEditText.text = Editable.Factory.getInstance().newEditable(place.address)

                }
                else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                    val status = Autocomplete.getStatusFromIntent(data)

                    Toast.makeText(applicationContext, status.statusMessage, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }


    override fun success() {

      Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, TripsummaryActivity::class.java))



    }

    override fun onStarted() {
        Toast.makeText(this, "started", Toast.LENGTH_SHORT).show()
    }

    override fun Failure() {
        Toast.makeText(this, "Please Fill all the fields", Toast.LENGTH_SHORT).show()
    }

    override fun CurrentLocation(currentlocation: TextView) {
        getCurrentLocation(currentlocation)
    }


}
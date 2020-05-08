package com.example.rideapp.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.rideapp.Interface
import com.example.rideapp.Model.Response
import com.example.rideapp.Model.ApiClass.RetrofitClient
import com.google.android.gms.maps.model.LatLng
import retrofit2.Call
import retrofit2.Callback


class CreateTripViewModel: ViewModel() {
    var Destination: String? = null
    var Source: String? = null
    var tripName: String? = null
    var startDate: String? = null
    var endDate: String? = null
    var time: String? = null
    var contact: String? = null

    var milestones: ArrayList<LatLng> = arrayListOf(LatLng(100.0, 200.0), LatLng(100.0, 500.0))
    var Destination2: LatLng = LatLng(300.0, 400.0)
    var source2: LatLng = LatLng(100.0, 200.0)

    var listener: Interface? = null


/*
        val createTripResponse = UserRepsitory().createTrip(tripName!!,
            Destination2,
            source2,
            startDate!!,
            endDate!!,
            time!!,
            milestones)*/

    fun onDoneButtonClick(view: View) {
        listener?.onStarted()
        if (Destination.isNullOrEmpty() || Source.isNullOrEmpty() || tripName.isNullOrEmpty() || startDate.isNullOrEmpty() || endDate.isNullOrEmpty() || time.isNullOrEmpty()) {
            listener?.Failure()
            return
        } else
            createTripApiCall()
        listener?.success()
    }

    private fun createTripApiCall() {
        RetrofitClient.createTripInstance.createTrip(
            tripName!!,
            Destination2,
            source2,
            startDate!!,
            endDate!!,
            time!!,
            milestones
        )
            .enqueue(object: Callback<Response>{
                override fun onFailure(call: Call<Response>, t: Throwable) {
                    Log.d("onfailure", t.message.toString())
                }

                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    Log.d("onResponse", call.toString() + "gg :  " + response.body()?.message.toString() + " " + response.body()?.statusCode.toString())
                }

            })

    }
}

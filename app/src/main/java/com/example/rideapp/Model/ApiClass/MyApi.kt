package com.example.rideapp.Model.ApiClass

import com.example.rideapp.Model.Response
import com.google.android.gms.maps.model.LatLng
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface MyApi {

    @FormUrlEncoded
    @POST("trip")
    fun createTrip(
        @Field(" tripName") tripName: String,
        @Field("destination") destination: LatLng,
        @Field("source") source: LatLng,
        @Field("startDate") startDate: String,
        @Field("endDate") endDate: String,
        @Field("startTime") startTime: String,
        @Field("milestones") milestones: ArrayList<LatLng>
    ) : Call<Response>

/*    companion object{
        operator  fun invoke(): MyApi{
            return Retrofit.Builder()
                .baseUrl("https://rideapp-robosoft.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }*/


}
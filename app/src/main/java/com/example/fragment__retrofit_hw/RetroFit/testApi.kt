package com.example.fragment__retrofit_hw.RetroFit

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface testApi {
    @GET("breeds/image/random") // replace with your API endpoint
    fun getRandomDogImage(): retrofit2.Call<DogResponse> // replace with your API response object
}


package com.example.fragment__retrofit_hw.RetroFit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface WeatherService {
    @GET("currentconditions/v1/{locationKey}")
    fun getCurrentWeather(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apiKey: String
    ): retrofit2.Call<List<CurrentWeather>>
}


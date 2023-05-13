package com.example.fragment__retrofit_hw.RetroFit

import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface WeatherService {
    @GET("currentconditions/v1/{locationKey}")
    fun getCurrentWeather(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apiKey: String
    ): retrofit2.Call<List<CurrentWeatherResponse>>

    @GET("locations/v1/cities/autocomplete")
    fun searchLocation(
        @Query("apikey") apiKey: String,
        @Query("q") searchText: String,
        @Query("language") language: String
    ): retrofit2.Call<List<LocationSearchResponse>>
}


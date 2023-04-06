package com.example.fragment__retrofit_hw.RetroFit

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("Temperature") val temperature: Temperature,
    @SerializedName("WeatherText") val weatherText: String
)

data class Temperature(
    @SerializedName("Metric") val metric: Metric
)

data class Metric(
    @SerializedName("Value") val value: Double
)
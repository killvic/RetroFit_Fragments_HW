package com.example.fragment__retrofit_hw.RetroFit

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("Temperature") val temperature: Temperature,
    @SerializedName("WeatherText") val weatherText: String
)

data class Temperature(
    @SerializedName("Metric") val metric: Metric
)

data class Metric(
    @SerializedName("Value") val value: Double
)


data class LocationSearchResponse(
    @SerializedName("Version") val version: Int,
    @SerializedName("Key") val key: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Rank") val rank: Int,
    @SerializedName("LocalizedName") val localizedName: String,
    @SerializedName("Country") val country: LocationSearchCountry,
    @SerializedName("AdministrativeArea") val admArea: LocationSearchAdministrativeArea
)

data class LocationSearchCountry(
    @SerializedName("ID") val id: String,
    @SerializedName("LocalizedName") val localizedName: String
)

data class LocationSearchAdministrativeArea(
    @SerializedName("ID") val id: String,
    @SerializedName("LocalizedName") val localizedName: String
)
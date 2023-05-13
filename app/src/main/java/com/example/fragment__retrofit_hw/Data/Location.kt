package com.example.fragment__retrofit_hw.Data


data class Location(
    val version: Int,
    val key: Int,
    val type: String,
    val rank: Int,
    val localizedName: String,
    val country: Country,
    val admArea: AdministrativeArea
) : DataItem

data class Country(
    val id: String,
    val localizedName: String
)

data class AdministrativeArea(
    val id: String,
    val localizedName: String
)

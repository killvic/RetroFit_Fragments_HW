package com.example.fragment__retrofit_hw.RetroFit

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("message") val imageUrl: String,
    @SerializedName("status") val status: String
)
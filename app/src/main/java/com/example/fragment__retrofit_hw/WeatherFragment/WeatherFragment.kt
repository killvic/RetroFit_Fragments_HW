package com.example.fragment__retrofit_hw.WeatherFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.fragment__retrofit_hw.R
import com.example.fragment__retrofit_hw.RetroFit.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class WeatherFragment : Fragment(R.layout.fragment_weather) {
    private lateinit var service: WeatherService
    private lateinit var tvTemperature: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_weather, container, false)

        val retrofitWeather = Retrofit.Builder()
            .baseUrl("http://dataservice.accuweather.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofitWeather.create(WeatherService::class.java)
        val locationKey = "215854"
        val apiKey = "PKwfVZI07NMwGAzxF46sGNzhfhyI9xIu"
        val callWeather = service.getCurrentWeather(locationKey, apiKey)
        callWeather.enqueue(object: Callback<List<CurrentWeather>> {
            override fun onResponse(call: Call<List<CurrentWeather>>, response: Response<List<CurrentWeather>>) {
                if (response.isSuccessful) {
                    val currentWeather = response.body()?.get(0)
                    tvTemperature.text = "${currentWeather?.temperature?.metric?.value} °C temperature in tel aviv"
                } else {
                    Log.e("WeatherFragment", "Response not successful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<CurrentWeather>>, t: Throwable) {
                Log.e("WeatherFragment", "Error fetching weather data", t)
            }
        })
        return rootView
    }
}
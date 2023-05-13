package com.example.fragment__retrofit_hw.AccuWeather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.fragment__retrofit_hw.Data.*
import com.example.fragment__retrofit_hw.R
import com.example.fragment__retrofit_hw.RetroFit.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

fun log(string: String) {
    Log.e("gg", string)
}

class WeatherFragment : Fragment(R.layout.fragment_weather) {
    private val apiKey = "PKwfVZI07NMwGAzxF46sGNzhfhyI9xIu"
    private var locations: List<LocationSearchResponse> = emptyList()
    private var curWeather: List<CurrentWeatherResponse> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_weather, container, false)
        val etSearchField =
            rootView.findViewById<EditText>(R.id.etSearchField) // make a onClickListener that will launch search fragment
        val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager)
        val retrofitWeather = Retrofit.Builder()
            .baseUrl("http://dataservice.accuweather.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofitWeather.create(WeatherService::class.java)

        val btTest = rootView.findViewById<Button>(R.id.btTest)
        btTest.setOnClickListener {
            viewPager.currentItem = 0 // calls for a location search fragment via test
        }

        log(locationCode.curLocationCode.toString() + " location code")
        val locationKey = locationCode.curLocationCode
        log(locationKey.toString())
        if (locationKey != 0)
            loadCurrentWeather(service, locationKey.toString(), rootView)

        return rootView
    }

    private fun loadCurrentWeather(service: WeatherService, locationKey: String, rootView: View) {
        service.getCurrentWeather(locationKey, apiKey)
            .enqueue(object : Callback<List<CurrentWeatherResponse>> {
                override fun onResponse(
                    call: Call<List<CurrentWeatherResponse>>,
                    response: Response<List<CurrentWeatherResponse>>
                ) {
                    if (response.isSuccessful) {
                        curWeather = response.body() ?: emptyList()
                        if (curWeather.isNotEmpty()) {
                            rootView.findViewById<TextView>(R.id.tvCurTemp).text =
                                getCurWeather()[0].temperature.metric.value.toString()
                        } else {
                            log("list is empty or null")
                        }
                    } else {
                        log("Cur Weather Error response: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<CurrentWeatherResponse>>, t: Throwable) {
                    log("API call failed: ${t.message}")
                }
            })
    }

    private fun getLocations(): List<LocationSearchResponse> {
        return locations
    }

    private fun getCurWeather(): List<CurrentWeatherResponse> {
        return curWeather
    }


    // Calls that i need:
    // - horizontal recycler for hourly weather 24 hours
    // - horizontal recycler for daily weather 7 days
}
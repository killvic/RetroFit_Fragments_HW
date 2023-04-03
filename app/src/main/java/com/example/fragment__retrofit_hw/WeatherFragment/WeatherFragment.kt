package com.example.fragment__retrofit_hw.WeatherFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.fragment__retrofit_hw.R
import com.example.fragment__retrofit_hw.RetroFit.DogResponse
import com.example.fragment__retrofit_hw.RetroFit.testApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherFragment : Fragment(R.layout.fragment_weather) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_weather, container, false)
        val imageView = rootView.findViewById<ImageView>(R.id.ivImage)
        // Do something with the ImageView

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/") // replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create()) // use appropriate converter for your API response format
            .build()

        val dogApi = retrofit.create(testApi::class.java)
        val call: Call<DogResponse> = dogApi.getRandomDogImage()
        call.enqueue(object: Callback<DogResponse> {
            override fun onResponse(call: Call<DogResponse>, response: Response<DogResponse>) {
                if (response.isSuccessful) {
                    val imageUrl = response.body()?.imageUrl
                    Glide.with(this@WeatherFragment)
                        .load(imageUrl)
                        .into(imageView)
                }
            }

            override fun onFailure(call: Call<DogResponse>, t: Throwable) {
                // TODO("Not yet implemented")
            }
        })
        return rootView
    }
}
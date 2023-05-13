package com.example.fragment__retrofit_hw.HelpFragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.fragment__retrofit_hw.AccuWeather.log
import com.example.fragment__retrofit_hw.Data.AdministrativeArea
import com.example.fragment__retrofit_hw.Data.Country
import com.example.fragment__retrofit_hw.Data.Location
import com.example.fragment__retrofit_hw.Data.locationCode
import com.example.fragment__retrofit_hw.R
import com.example.fragment__retrofit_hw.RecyclerView.RecyclerAdapter
import com.example.fragment__retrofit_hw.RetroFit.LocationSearchResponse
import com.example.fragment__retrofit_hw.RetroFit.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val adapter = RecyclerAdapter()
    private val apiKey = "PKwfVZI07NMwGAzxF46sGNzhfhyI9xIu"
    private var locations: List<LocationSearchResponse> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)
        val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager)

        val retrofitWeather = Retrofit.Builder()
            .baseUrl("http://dataservice.accuweather.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofitWeather.create(WeatherService::class.java)

        val etSearchField = rootView.findViewById<EditText>(R.id.etSearchField)

        val recyclerView: RecyclerView = rootView.findViewById(R.id.rvLocations)
        recyclerView.adapter = adapter
        adapter.onClick = { // Re-writes global var locationCode
            // it refers to location code, so after click i just need to pass "it" back to weather fragment
            locationCode.curLocationCode = it
            log(it.toString() + " onClick it")
            log(locationCode.curLocationCode.toString() + " onClick it")
            viewPager.currentItem = 1
        }
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        inputApiCall(etSearchField, service)
        return rootView
    }

    private fun loadLocations(service: WeatherService, query: String) {
        service.searchLocation(apiKey, query, "en-us").enqueue(object :
            Callback<List<LocationSearchResponse>> {
            override fun onResponse(
                call: Call<List<LocationSearchResponse>>,
                response: Response<List<LocationSearchResponse>>
            ) {
                if (response.isSuccessful) {
                    locations = response.body() ?: emptyList()
                    if (locations.isNotEmpty()) {
                        adapter.itemList = convertLocationResponseToList(getLocations())
                        adapter.notifyDataSetChanged()
                    } else {
                        log("list is empty or null")
                    }
                } else {
                    log("Error response: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<LocationSearchResponse>>, t: Throwable) {
                log("API call failed: ${t.message}")
            }
        })
    }

    private fun getLocations(): List<LocationSearchResponse> {
        return locations
    }

    private fun convertLocationResponseToList(responseLocations: List<LocationSearchResponse>): List<Location> {
        val resultList = mutableListOf<Location>()

        for (location in responseLocations) {
            val country =
                Country(id = location.country.id, localizedName = location.country.localizedName)
            val admArea = AdministrativeArea(
                id = location.admArea.id,
                localizedName = location.admArea.localizedName
            )
            val result = Location(
                version = location.version,
                key = location.key.toInt(),
                type = location.type,
                rank = location.rank,
                localizedName = location.localizedName,
                country = country,
                admArea = admArea
            )
            resultList.add(result)
        }

        return resultList
    }

    private fun inputApiCall(etSearchField: EditText, service: WeatherService) {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Do nothing
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Do nothing
            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    loadLocations(service, s.toString())
                }
            }
        }
        etSearchField.addTextChangedListener(textWatcher)
    }
}
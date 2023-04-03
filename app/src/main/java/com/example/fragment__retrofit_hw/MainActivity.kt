package com.example.fragment__retrofit_hw

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.fragment__retrofit_hw.FBIfragment.FBIfragment
import com.example.fragment__retrofit_hw.RetroFit.DogResponse
import com.example.fragment__retrofit_hw.RetroFit.testApi
import com.example.fragment__retrofit_hw.TwilioFragment.TwilioFragment
import com.example.fragment__retrofit_hw.WeatherFragment.WeatherFragment
import com.example.fragment_viewpager_sandbox.FragmentPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    private lateinit var botNavMenu: BottomNavigationView
    private lateinit var viewPager: ViewPager2
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = FragmentPagerAdapter(this)
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter
        //viewPager.isUserInputEnabled = false disables ALL user input

        viewPager.isUserInputEnabled = true // Disables only swipes
        viewPager.setOnTouchListener { v, event ->
            true // Consume all touch events
        }

        botNavMenu = findViewById(R.id.bottomNavigationView)

        botNavMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.miWeather -> {
                    supportFragmentManager.beginTransaction().apply{
                        viewPager.currentItem = 0
                        addToBackStack(null)
                        commit()
                    }
                }
                R.id.miTwilio -> {
                    supportFragmentManager.beginTransaction().apply{
                        viewPager.currentItem = 1
                        addToBackStack(null)
                        commit()
                    }
                }
                R.id.miFbi -> {
                    supportFragmentManager.beginTransaction().apply{
                        viewPager.currentItem = 2
                        addToBackStack(null)
                        commit()
                    }
                }
            }
            true
        }
    }
}
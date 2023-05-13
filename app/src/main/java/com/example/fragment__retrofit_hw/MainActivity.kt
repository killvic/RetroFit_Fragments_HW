package com.example.fragment__retrofit_hw

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.example.fragment_viewpager_sandbox.FragmentPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.*

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
        viewPager.currentItem = 1
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
                        viewPager.currentItem = 1
                        addToBackStack(null)
                        commit()
                    }
                }
                R.id.miTwilio -> {
                    supportFragmentManager.beginTransaction().apply{
                        viewPager.currentItem = 2
                        addToBackStack(null)
                        commit()
                    }
                }
                R.id.miFbi -> {
                    supportFragmentManager.beginTransaction().apply{
                        viewPager.currentItem = 3
                        addToBackStack(null)
                        commit()
                    }
                }
            }
            true
        }
    }

}
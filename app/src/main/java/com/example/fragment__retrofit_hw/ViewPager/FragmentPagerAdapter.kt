package com.example.fragment_viewpager_sandbox

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragment__retrofit_hw.FBIfragment.FBIfragment
import com.example.fragment__retrofit_hw.TwilioFragment.TwilioFragment
import com.example.fragment__retrofit_hw.AccuWeather.WeatherFragment
import com.example.fragment__retrofit_hw.HelpFragments.SearchFragment


class FragmentPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> WeatherFragment()
            2 -> TwilioFragment()
            3 -> FBIfragment()
            0 -> SearchFragment()
            else -> throw IllegalArgumentException("Invalid view pager position: $position")
        }
    }
}

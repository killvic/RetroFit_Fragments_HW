package com.example.fragment_viewpager_sandbox

import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragment__retrofit_hw.FBIfragment.FBIfragment
import com.example.fragment__retrofit_hw.R
import com.example.fragment__retrofit_hw.TwilioFragment.TwilioFragment
import com.example.fragment__retrofit_hw.WeatherFragment.WeatherFragment


class FragmentPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WeatherFragment()
            1 -> TwilioFragment()
            2 -> FBIfragment()
            else -> throw IllegalArgumentException("Invalid view pager position: $position")
        }
    }
}

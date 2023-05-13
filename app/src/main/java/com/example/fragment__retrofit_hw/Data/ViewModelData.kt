package com.example.fragment__retrofit_hw.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

object ViewModelData: ViewModel() {
    private val locationCode: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun setLocationCode(code: Int) {
        locationCode.value = code
    }

    fun getLocationCode(): Int {
        return locationCode.value ?: 0
    }
}
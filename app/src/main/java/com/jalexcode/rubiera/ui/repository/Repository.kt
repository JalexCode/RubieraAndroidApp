package com.jalexcode.rubiera.ui.repository

import OpenWeatherMap
import com.jalexcode.rubiera.data.api.ApiService

class Repository(val api:ApiService) {
    suspend fun fetchWeatherWithCityName(cityname:String): OpenWeatherMap? {
        val response = api.fetchWeatherWithCityName(cityname)
        if (response!!.isSuccessful){
            return response.body()
        }
        return null
    }
}
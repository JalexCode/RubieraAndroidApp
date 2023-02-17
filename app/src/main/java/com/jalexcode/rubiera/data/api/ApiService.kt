package com.jalexcode.rubiera.data.api

import OpenWeatherMap
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather?appid=e0e4d96e41aa0691fc64da22726a39d6&units=metric")
    suspend fun fetchWeatherWithLocation(@Query("lat") lat: Double, @Query("lon") lon: Double): Response<OpenWeatherMap>?
    @GET("weather?appid=e0e4d96e41aa0691fc64da22726a39d6&units=metric")
    suspend fun fetchWeatherWithCityName(@Query("q") name: String?): Response<OpenWeatherMap>?
    companion object{
        val baseUrl = "https://api.openweathermap.org/data/2.5/"
        fun getInstance(): ApiService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
        }
    }
}
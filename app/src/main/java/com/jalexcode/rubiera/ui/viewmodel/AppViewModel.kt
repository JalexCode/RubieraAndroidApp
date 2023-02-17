package com.jalexcode.rubiera.ui.viewmodel

import OpenWeatherMap
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jalexcode.rubiera.data.api.ApiService
import com.jalexcode.rubiera.ui.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppViewModel (application: Application):AndroidViewModel(application) {
    private val repository by lazy { Repository(ApiService.getInstance()) }

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    lateinit var weatherLiveData : MutableLiveData<OpenWeatherMap>

    val showGrid = MutableStateFlow(false)

    init {
        weatherLiveData = MutableLiveData()
    }

    fun getWeatherObserver():MutableLiveData<OpenWeatherMap>{
        return weatherLiveData;
    }

    fun fetchWeatherWithCityName(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val result = repository.fetchWeatherWithCityName(city)
                //
                weatherLiveData.postValue(result)
                _isLoading.value = false
            } catch (e: Exception) {
                Log.d("Error while GET", e.message!!)
                _isLoading.value = false
            }
        }
    }
}
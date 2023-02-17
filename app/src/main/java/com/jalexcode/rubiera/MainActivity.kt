package com.jalexcode.rubiera

import OpenWeatherMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jalexcode.rubiera.data.Util
import com.jalexcode.rubiera.databinding.ActivityMainBinding
import com.jalexcode.rubiera.ui.viewmodel.AppViewModel
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 
        val viewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        viewModel.getWeatherObserver().observe(this, Observer<OpenWeatherMap> {
            if (it != null){
                binding.actualWeatherFastview.actualTemperature.text = "${it.main.temp.roundToInt()}"
                binding.actualWeatherFastview.minTemperature.text = "${it.main.temp_min.roundToInt()}"
                binding.actualWeatherFastview.maxTemperature.text = "${it.main.temp_max.roundToInt()}"
                //
                binding.actualWeatherFastview.progno.text = it.weather.get(0).main
                binding.actualWeatherFastview.humedity.text = "Humedad: ${it.main.humidity}"
                binding.actualWeatherFastview.realFeel.text = "Humedad: ${it.main.feels_like}ºC"
            }else{
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.fetchWeatherWithCityName("Camagüey")
        //
    }
}
package com.arabadalyan.openweathermap.view.fragments.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arabadalyan.domain.model.Resource
import com.arabadalyan.domain.model.Weather
import com.arabadalyan.domain.usecase.WeatherUseCase
import com.arabadalyan.openweathermap.constants.MAP_KEY
import com.arabadalyan.openweathermap.view.fragments.base.BaseViewModel
import kotlinx.coroutines.*

class WeatherViewModel(private var weatherUseCase: WeatherUseCase) : BaseViewModel() {

    private var _weather: MutableLiveData<Resource<Weather?>> = MutableLiveData<Resource<Weather?>>()
    val weather: LiveData<Resource<Weather?>>
    get() = _weather

    private var job: Job? = null

    /**
     * Depend on isOnline value get weather from api or from database using coroutines
     */
    fun getWeather(lat: Double, lon: Double, exclude: String, units: String, isOnline: Boolean) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = weatherUseCase.execute(lat, lon, exclude, units, MAP_KEY, isOnline)
            withContext(Dispatchers.Main) {
                _weather.value = result

            }
        } // waiting for job to complete...
    }

    /**
     * Cancel the job
     */
    fun cancelJob() {
        job?.cancel()
    }
}

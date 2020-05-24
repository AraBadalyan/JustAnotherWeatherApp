package com.arabadalyan.domain.usecase

import com.arabadalyan.domain.model.Resource
import com.arabadalyan.domain.model.Weather
import com.arabadalyan.domain.repositroy.WeatherRepository

/**
 * There should be a business logic of weather
 */
class WeatherUseCase(private val weatherRepository: WeatherRepository) {

    /**
     * Execute weather depend on isOnline value, or from API or from DB
     */
    suspend fun execute(lat: Double, lon: Double, exclude: String, units: String, appId: String, isOnline: Boolean): Resource<Weather?> {
        return if (isOnline) {
            weatherRepository.getWeatherFromApi(lat, lon, exclude, units, appId)
        } else {
            weatherRepository.getWeatherFromDb()
        }
    }
}
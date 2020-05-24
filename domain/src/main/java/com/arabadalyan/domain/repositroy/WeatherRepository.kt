package com.arabadalyan.domain.repositroy

import com.arabadalyan.domain.model.Resource
import com.arabadalyan.domain.model.Weather

interface WeatherRepository {

    suspend fun getWeatherFromApi(
        lat: Double,
        lon: Double,
        exclude: String,
        units: String,
        appid: String
    ): Resource<Weather?>

    suspend fun getWeatherFromDb(): Resource<Weather?>
}
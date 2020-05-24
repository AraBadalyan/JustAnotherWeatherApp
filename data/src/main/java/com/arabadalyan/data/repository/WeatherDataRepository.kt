package com.arabadalyan.data.repository

import com.arabadalyan.data.db.dao.WeatherDao
import com.arabadalyan.data.entity.WeatherEntity
import com.arabadalyan.data.mapper.WeatherMapper.transformToWeather
import com.arabadalyan.data.net.RequestClientBuilder
import com.arabadalyan.data.net.ResponseHandler
import com.arabadalyan.domain.model.Resource
import com.arabadalyan.domain.model.Weather
import com.arabadalyan.domain.repositroy.WeatherRepository

class WeatherDataRepository(private val weatherDao: WeatherDao, private val responseHandler: ResponseHandler) : WeatherRepository {

    /**
     * Get weather from api and store it to db
     */
    override suspend fun getWeatherFromApi(
        lat: Double,
        lon: Double,
        exclude: String,
        units: String,
        appid: String
    ): Resource<Weather?> {
        return try {
            val weatherEntity = RequestClientBuilder.WEATHER_API.getWeather(lat, lon, exclude, units, appid)
            insertToDb(weatherEntity)
            val weather =  weatherEntity.transformToWeather()
            responseHandler.handleSuccess(weather)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    /**
     * store weather to room database
     */
    private suspend fun insertToDb(weatherEntity: WeatherEntity) {
        weatherDao.insertOrUpdate(weatherEntity)
    }

    /**
     * Get weather from room database
     * Returning Weather inside Resource because of error handling
     */
    override suspend fun getWeatherFromDb(): Resource<Weather?> {
        return try {
            val weather =  weatherDao.getWeather()?.transformToWeather()
            responseHandler.handleSuccess(weather)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}
package com.arabadalyan.data.mapper

import com.arabadalyan.data.constant.IMAGE_BASE_URL
import com.arabadalyan.data.entity.DailyEntity
import com.arabadalyan.data.entity.WeatherDetailsEntity
import com.arabadalyan.data.entity.WeatherEntity
import com.arabadalyan.domain.model.Daily
import com.arabadalyan.domain.model.Weather
import java.text.SimpleDateFormat
import java.util.*

/**
 * Map WeatherEntity to UI friendly Weather
 */
object WeatherMapper {
    fun WeatherEntity.transformToWeather() = Weather(
        timezone = timezone,
        daily = daily.map { it.transformToDaily() }
    )

    private fun DailyEntity.transformToDaily() = Daily(
        currentDay = getCurrentDay(dt),
        imagePath = getImagePath(weather[0].icon),
        maxDegree = temp.max.toInt().toString(),
        minDegree = temp.min.toInt().toString(),
        description = getDescription(weather)
    )

    private fun getCurrentDay(dt: Long): String {
        val sdf = SimpleDateFormat("EEEE dd/M")
        val dateFormat = Date(dt * 1000)
        val weekday: String = sdf.format(dateFormat)
        return weekday
    }

    private fun getImagePath(icon: String): String {
        return "$IMAGE_BASE_URL$icon@2x.png"
    }

    private fun getDescription(weather: List<WeatherDetailsEntity>): String {
        var description = ""
        for (i in weather.indices) {
            if (i != 0) {
                description += ","
            }
            description += weather[i].description
        }
        return description
    }
}
package com.arabadalyan.data.entity

import androidx.room.Entity

@Entity
data class DailyEntity(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: TempEntity,
    val weather: List<WeatherDetailsEntity>
)
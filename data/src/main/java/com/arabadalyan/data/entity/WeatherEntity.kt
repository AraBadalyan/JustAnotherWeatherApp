package com.arabadalyan.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weatherEntity")
data class WeatherEntity(
    @PrimaryKey
    val id: Int = 1001,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val daily: List<DailyEntity>
)
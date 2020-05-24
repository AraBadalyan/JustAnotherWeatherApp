package com.arabadalyan.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherDetailsEntity(
    @PrimaryKey
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
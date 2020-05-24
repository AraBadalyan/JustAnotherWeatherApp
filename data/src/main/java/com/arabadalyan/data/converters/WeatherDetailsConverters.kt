package com.arabadalyan.data.converters

import androidx.room.TypeConverter
import com.arabadalyan.data.entity.WeatherDetailsEntity
import com.squareup.moshi.Moshi

class WeatherDetailsConverters {

    @TypeConverter
    fun weatherDetailsEntityToString(input: List<WeatherDetailsEntity>?): String {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter<List<WeatherDetailsEntity>>(WeatherDetailsEntity::class.java)
        return adapter.toJson(input)
    }

    @TypeConverter
    fun stringToWeatherDetailsEntity(input: String) : List<WeatherDetailsEntity>? =
        Moshi.Builder().build().adapter<List<WeatherDetailsEntity>>(WeatherDetailsEntity::class.java).fromJson(input)
}
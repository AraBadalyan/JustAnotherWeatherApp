package com.arabadalyan.data.db.dao

import androidx.room.*
import com.arabadalyan.data.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(weatherEntity: WeatherEntity)

    @Delete
    suspend fun delete(weatherEntity: WeatherEntity)

    @Update
    suspend fun update(weatherEntity: WeatherEntity)

    @Query("Select * FROM weatherEntity LIMIT 1")
    suspend fun getWeather(): WeatherEntity?
}
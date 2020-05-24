package com.arabadalyan.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arabadalyan.data.converters.DailyConverters
import com.arabadalyan.data.converters.TempConverter
import com.arabadalyan.data.converters.WeatherDetailsConverters
import com.arabadalyan.data.db.dao.WeatherDao
import com.arabadalyan.data.entity.WeatherEntity

private const val DATABASE = "weather_db"

/**
 * Creating database
 * Use Type converters because room cant store custom types
 */
@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(DailyConverters::class, TempConverter::class, WeatherDetailsConverters::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WeatherDatabase {
            return Room.databaseBuilder(context, WeatherDatabase::class.java, DATABASE)
                .build()
        }
    }
}
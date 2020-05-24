package com.arabadalyan.data.converters

import androidx.room.TypeConverter
import com.arabadalyan.data.entity.DailyEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class DailyConverters {

    @TypeConverter
    fun dailyEntityToString(input: List<DailyEntity>?): String? {
        val moshi = Moshi.Builder().build()

        val listType = Types.newParameterizedType(List::class.java, DailyEntity::class.java)
        val adapter: JsonAdapter<List<DailyEntity>> = moshi.adapter(listType)
        val result = adapter.toJson(input)
        return result



//        val adapter = moshi.adapter<List<DailyEntity>>(DailyEntity::class.java)
//        return adapter.toJson(input)
    }

    @TypeConverter
    fun stringToDailyEntity(input: String?) : List<DailyEntity>? {
        val moshi = Moshi.Builder().build()

        val listType = Types.newParameterizedType(List::class.java, DailyEntity::class.java)
        val adapter: JsonAdapter<List<DailyEntity>> = moshi.adapter(listType)
        val result = adapter.fromJson(input)
        return result
    }
//        Moshi.Builder().build().adapter<List<DailyEntity>>(DailyEntity::class.java).fromJson(input)
}
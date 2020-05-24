package com.arabadalyan.data.converters

import androidx.room.TypeConverter
import com.arabadalyan.data.entity.TempEntity
import com.squareup.moshi.Moshi

class TempConverter {

    @TypeConverter
    fun tempEntityToString(input: TempEntity?): String {
        return Moshi.Builder().build().adapter(TempEntity::class.java).toJson(input)
    }

    @TypeConverter
    fun stringToTempEntity(input: String) : TempEntity? =
        Moshi.Builder().build().adapter(TempEntity::class.java).fromJson(input)
}
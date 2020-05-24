package com.arabadalyan.data.entity

import androidx.room.Entity

@Entity
data class TempEntity(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)
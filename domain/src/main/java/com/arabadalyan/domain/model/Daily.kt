package com.arabadalyan.domain.model

import java.io.Serializable

data class Daily(
    val currentDay: String,
    val imagePath: String,
    val maxDegree: String,
    val minDegree: String,
    val description: String
//    val temp: Temp
) : Serializable
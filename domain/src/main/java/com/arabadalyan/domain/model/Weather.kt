package com.arabadalyan.domain.model

import java.io.Serializable

data class Weather(
    val timezone: String,
    val daily: List<Daily>
): Serializable
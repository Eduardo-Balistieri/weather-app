package com.example.weather.domain.entities

import java.util.*

data class Forecast(
    val condition: Condition,
    val temperature: Int,   // ºC
    val date: Calendar,
    val humidity: Int,      // %
    val precipitation: Int  // chance of precipitation
)

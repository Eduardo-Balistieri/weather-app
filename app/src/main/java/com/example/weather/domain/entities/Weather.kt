package com.example.weather.domain.entities

data class Weather(
    val condition: Condition,
    val temperature: Int,   // ºC
    val location: Location,

    val forecasts: List<Forecast>
)

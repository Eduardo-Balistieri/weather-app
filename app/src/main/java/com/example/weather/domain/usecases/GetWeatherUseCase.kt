package com.example.weather.domain.usecases

import com.example.weather.data.WeatherRepository
import com.example.weather.domain.entities.Location
import com.example.weather.domain.entities.Weather
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(location: Location): Weather {
        return repository.getWeather(location)
    }
}

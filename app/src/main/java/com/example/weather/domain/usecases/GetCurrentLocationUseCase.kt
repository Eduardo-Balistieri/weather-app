package com.example.weather.domain.usecases

import com.example.weather.data.WeatherRepository
import com.example.weather.domain.entities.Location
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(): Location {
        return repository.getCurrentLocation()
    }
}

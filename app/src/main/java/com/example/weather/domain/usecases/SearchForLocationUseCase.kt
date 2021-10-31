package com.example.weather.domain.usecases

import com.example.weather.data.WeatherRepository
import com.example.weather.domain.entities.Location
import javax.inject.Inject

class SearchForLocationUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(searchQuery: String): List<Location> {
        return repository.searchForLocation(searchQuery)
    }
}

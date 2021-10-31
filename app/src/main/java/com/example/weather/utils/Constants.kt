package com.example.weather.utils

import com.example.weather.domain.entities.Location

object Constants {
    val LOCATIONS = listOf(
        Location(country = "Argentina", city = "Buenos Aires"),
        Location(country = "Brazil", city = "Brasilia"),
        Location(country = "Brazil", city = "Rio de Janeiro"),
        Location(country = "Brazil", city = "São Paulo"),
        Location(country = "Canada", city = "Ottawa"),
        Location(country = "Canada", city = "Vancouver"),
        Location(country = "Canada", city = "Toronto"),
        Location(country = "China", city = "Beijing"),
        Location(country = "Denmark", city = "Copenhagen"),
        Location(country = "England", city = "London"),
        Location(country = "France", city = "Paris"),
        Location(country = "Germany", city = "Berlin"),
        Location(country = "Ireland", city = "Dublin"),
        Location(country = "Italy", city = "Rome"),
        Location(country = "Japan", city = "Tokyo"),
        Location(country = "Japan", city = "Yokohama"),
        Location(country = "Japan", city = "Kyoto"),
        Location(country = "Mexico", city = "Mexico City"),
        Location(country = "Poland", city = "Krakow"),
        Location(country = "Portugal", city = "Lisbon"),
        Location(country = "Russia", city = "Moscow"),
        Location(country = "Switzerland", city = "Bern"),
        Location(country = "United States", city = "Washington D.C"),
        Location(country = "United States", city = "New York"),
        Location(country = "United States", city = "Los Angeles"),
        Location(country = "United States", city = "San Francisco"),
        Location(country = "United States", city = "Chicago")
    )

    const val FORECASTS_COUNT = 7

    object ROUTES {
        const val WEATHER_LOCATION_KEY = "location"
        const val WEATHER_SCREEN = "weather_screen/{location}"

        const val SEARCH_SCREEN = "search_screen"
    }
}

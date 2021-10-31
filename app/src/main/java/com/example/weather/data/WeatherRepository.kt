package com.example.weather.data

import com.example.weather.domain.entities.Condition
import com.example.weather.domain.entities.Forecast
import com.example.weather.domain.entities.Location
import com.example.weather.domain.entities.Weather
import com.example.weather.utils.Constants.LOCATIONS
import com.example.weather.utils.Constants.FORECASTS_COUNT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*

@InstallIn(SingletonComponent::class)
@Module
class WeatherRepository {

    @Provides
    fun provides(): WeatherRepository {
        return WeatherRepository()
    }


    fun getCurrentLocation(): Location {
        return LOCATIONS[Random().nextInt(LOCATIONS.size)]
    }

    fun getWeather(location: Location): Weather {
        val conditions = Condition.values()
        val condition = conditions[Random().nextInt(conditions.size)]

        val temperature = when(condition) {
            Condition.SNOWY -> (-5 until 1).random()
            else -> (0 until 31).random()
        }

        return Weather(
            condition = condition,
            temperature = temperature,
            location = location,
            forecasts = generateForecasts()
        )
    }

    fun searchForLocation(searchQuery: String): List<Location> {
        val locale = Locale.getDefault()
        val lSearchQuery = searchQuery.lowercase(locale)

        val searchResult = mutableListOf<Location>()

        for(location in LOCATIONS) {
            if(
                location.city.lowercase(locale).contains(lSearchQuery) ||
                location.country.lowercase(locale).contains(lSearchQuery)
            ) {
                searchResult.add(location)
            }
        }
        return searchResult
    }

    // ***

    private fun generateForecasts(): List<Forecast> {
        val forecasts = mutableListOf<Forecast>()
        for(index in 1..FORECASTS_COUNT) {
            val conditions = Condition.values()
            val condition = conditions[Random().nextInt(conditions.size)]

            val temperature = when(condition) {
                Condition.SNOWY -> (-5 until 1).random()
                else -> (0 until 31).random()
            }

            val date = Calendar.getInstance()
            date.add(Calendar.DATE, index)

            forecasts.add(
                Forecast(
                    condition = condition,
                    temperature = temperature,
                    date = date,
                    humidity = (10 until 91).random(),
                    precipitation = (0 until 101).random()
                )
            )
        }
        return forecasts
    }
}

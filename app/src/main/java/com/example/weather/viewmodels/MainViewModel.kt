package com.example.weather.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.weather.domain.entities.Location
import com.example.weather.domain.entities.Weather
import com.example.weather.domain.usecases.GetCurrentLocationUseCase
import com.example.weather.domain.usecases.GetWeatherUseCase
import com.example.weather.domain.usecases.SearchForLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val searchForLocationUseCase: SearchForLocationUseCase
): ViewModel() {

    private val _currentWeather: MutableState<Weather> = mutableStateOf(
        getWeatherUseCase(getCurrentLocationUseCase())
    )
    val currentWeather: State<Weather> = _currentWeather



    fun getWeather(location: Location): Weather {
        return if(location == _currentWeather.value.location)
            _currentWeather.value
        else
            getWeatherUseCase(location)
    }

    fun searchForLocation(searchQuery: String): List<Location> {
        return searchForLocationUseCase(searchQuery)
    }
}

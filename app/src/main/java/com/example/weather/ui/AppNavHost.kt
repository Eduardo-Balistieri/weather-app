package com.example.weather.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weather.domain.entities.Location
import com.example.weather.ui.screens.weather.WeatherScreen
import com.example.weather.ui.screens.search.SearchScreen
import com.example.weather.utils.Constants.ROUTES
import com.example.weather.viewmodels.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.gson.Gson

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun AppNavHost(
    mainViewModel: MainViewModel
) {
    val navController = rememberNavController()
    val gson = Gson()

    NavHost(
        navController = navController,
        startDestination = ROUTES.WEATHER_SCREEN
    ) {
        composable(
            route = ROUTES.WEATHER_SCREEN,
            arguments = listOf(
                navArgument(ROUTES.WEATHER_LOCATION_KEY) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val location = backStackEntry.arguments?.getString(ROUTES.WEATHER_LOCATION_KEY)
            val isInitialScreen = location.isNullOrBlank()

            val currentWeather = if(location.isNullOrBlank()) {
                mainViewModel.currentWeather.value
            }
            else {
                val parsedLocation = gson.fromJson(location, Location::class.java)
                mainViewModel.getWeather(location = parsedLocation)
            }

            WeatherScreen(
                currentWeather = currentWeather,
                navController = navController,
                isInitialScreen = isInitialScreen
            )
        }
        composable(route = ROUTES.SEARCH_SCREEN) {
            SearchScreen(
                mainViewModel = mainViewModel,
                navigateToWeatherScreen = { location ->
                    val parsedLocation = gson.toJson(location)
                    navController.navigate(
                        "weather_screen/$parsedLocation"
                    )
                }
            )
        }
    }
}

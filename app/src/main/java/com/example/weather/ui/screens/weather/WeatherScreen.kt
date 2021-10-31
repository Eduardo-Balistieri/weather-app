package com.example.weather.ui.screens.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.weather.R
import com.example.weather.domain.entities.Weather
import com.example.weather.ui.screens.weather.current.CurrentWeather
import com.example.weather.ui.screens.weather.forecasts.Forecasts
import com.example.weather.ui.theme.getWeatherColors
import com.example.weather.utils.Constants.ROUTES
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun WeatherScreen(
    currentWeather: Weather,
    navController: NavController,
    isInitialScreen: Boolean
) {
    val scope = rememberCoroutineScope()

    val tabTitles = listOf(
        stringResource(id = R.string.today),
        stringResource(id = R.string.next_days)
    )
    val pagerState = rememberPagerState(initialPage = 0)

    val weatherColors = getWeatherColors(currentWeather.condition)

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = weatherColors.secondary,
            contentColor = weatherColors.text
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            color = weatherColors.text
                        )
                    },
                    selected = index == pagerState.currentPage,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        HorizontalPager(
            count = 2,
            state = pagerState
        ) { pageIndex ->
            when(pageIndex) {
                0 -> CurrentWeather(
                    currentWeather = currentWeather,
                    navigateToSearchScreen = {
                        navController.navigate(ROUTES.SEARCH_SCREEN)
                    },
                    isInitialScreen = isInitialScreen
                )
                else -> Forecasts(
                    forecasts = currentWeather.forecasts
                )
            }
        }
    }
}

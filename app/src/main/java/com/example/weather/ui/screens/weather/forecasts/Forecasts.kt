package com.example.weather.ui.screens.weather.forecasts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.domain.entities.Condition
import com.example.weather.domain.entities.Forecast
import com.example.weather.ui.theme.Black
import java.util.*

@ExperimentalMaterialApi
@Composable
fun Forecasts(
    forecasts: List<Forecast>
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Black)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 10.dp,
                vertical = 15.dp,
            )
        ) {
            items(forecasts) { forecast ->
                ForecastRowItem(forecast = forecast)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun ForecastsPreview() {
    Forecasts(
        forecasts = listOf(
            Forecast(
                condition = Condition.SUNNY,
                temperature = 15,
                date = Calendar.getInstance(),
                humidity = 90,
                precipitation = 100
            ),
            Forecast(
                condition = Condition.RAINY,
                temperature = 15,
                date = Calendar.getInstance(),
                humidity = 90,
                precipitation = 100
            ),
            Forecast(
                condition = Condition.CLOUDY,
                temperature = 15,
                date = Calendar.getInstance(),
                humidity = 90,
                precipitation = 100
            ),
            Forecast(
                condition = Condition.STORMY,
                temperature = 15,
                date = Calendar.getInstance(),
                humidity = 90,
                precipitation = 100
            ),
            Forecast(
                condition = Condition.WINDY,
                temperature = 15,
                date = Calendar.getInstance(),
                humidity = 90,
                precipitation = 100
            ),
            Forecast(
                condition = Condition.SNOWY,
                temperature = 15,
                date = Calendar.getInstance(),
                humidity = 90,
                precipitation = 100
            )
        )
    )
}

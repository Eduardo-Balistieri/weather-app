package com.example.weather.domain.entities

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.weather.R

enum class Condition {
    SUNNY,
    RAINY,
    CLOUDY,
    STORMY,
    WINDY,
    SNOWY
}

@Composable
fun Condition.toFormattedString(): String {
    return stringResource(
        when(this) {
            Condition.SUNNY -> R.string.sunny
            Condition.RAINY -> R.string.rainy
            Condition.CLOUDY -> R.string.cloudy
            Condition.STORMY -> R.string.stormy
            Condition.WINDY -> R.string.windy
            Condition.SNOWY -> R.string.snowy
        }
    )
}

fun Condition.getWeatherIcon(): Int {
    return when(this) {
        Condition.SUNNY -> R.drawable.ic_sunny
        Condition.RAINY -> R.drawable.ic_rainy
        Condition.CLOUDY -> R.drawable.ic_cloudy
        Condition.STORMY -> R.drawable.ic_stormy
        Condition.WINDY -> R.drawable.ic_windy
        Condition.SNOWY -> R.drawable.ic_snowy
    }
}

package com.example.weather.ui.screens.weather.current

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.domain.entities.*
import com.example.weather.ui.theme.getWeatherColors
import com.example.weather.utils.toFormattedString
import java.util.*

@Composable
fun CurrentWeather(
    currentWeather: Weather,
    navigateToSearchScreen: () -> Unit,
    isInitialScreen: Boolean
) {
    val weatherColors = getWeatherColors(currentWeather.condition)
    var weatherConditionTextWidth by remember { mutableStateOf(0) }

    Scaffold(
        floatingActionButton = {
            if(isInitialScreen) {
                FloatingActionButton(
                    onClick = { navigateToSearchScreen() },
                    backgroundColor = weatherColors.icon
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = stringResource(id = R.string.search_icon),
                        tint = weatherColors.primary
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(weatherColors.primary)
                .padding(horizontal = 10.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = Calendar.getInstance().toFormattedString(),
                            color = weatherColors.text,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = currentWeather.location.city,
                            color = weatherColors.text,
                            fontSize = 26.sp,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = currentWeather.location.country,
                            color = weatherColors.text
                        )
                    }
                }
                Text(
                    color = weatherColors.text,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .onGloballyPositioned { coordinates ->
                            weatherConditionTextWidth = coordinates.size.width
                        }
                        .graphicsLayer(
                            transformOrigin = TransformOrigin(
                                pivotFractionX = 0F,
                                pivotFractionY = 0F
                            ),
                            rotationZ = 90F,
                            translationX = weatherConditionTextWidth.toFloat()
                        ),
                    maxLines = 1,
                    text = currentWeather.condition.toFormattedString()
                )
            }
            Icon(
                painter = painterResource(
                    id = currentWeather.condition.getWeatherIcon()
                ),
                contentDescription = stringResource(id = R.string.current_weather_icon),
                tint = weatherColors.icon,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .fillMaxHeight(0.5F)
                    .fillMaxWidth()
            )

            Row (
                modifier = Modifier
                    .wrapContentHeight()
            ) {
                Text(
                    text = currentWeather.temperature.toString(),
                    color = weatherColors.text,
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = "ºC",
                    color = weatherColors.text,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .align(BiasAlignment.Vertical(-0.45f))
                )
            }
        }
    }
}

@Composable
@Preview
fun CurrentWeatherPreview() {
    CurrentWeather(
        currentWeather = Weather(
            condition = Condition.RAINY,
            temperature = 20,
            location = Location(city = "New York", country = "United States"),
            forecasts = listOf(
                Forecast(
                    condition = Condition.RAINY,
                    temperature = 15,
                    date = Calendar.getInstance(),
                    humidity = 90,
                    precipitation = 100
                )
            )
        ),
        navigateToSearchScreen = { },
        isInitialScreen = true
    )
}

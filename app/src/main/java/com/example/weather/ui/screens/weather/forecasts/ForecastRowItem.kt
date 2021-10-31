package com.example.weather.ui.screens.weather.forecasts

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.weather.R
import com.example.weather.domain.entities.Condition
import com.example.weather.domain.entities.Forecast
import com.example.weather.domain.entities.getWeatherIcon
import com.example.weather.ui.theme.Black
import com.example.weather.ui.theme.CardBackground
import com.example.weather.ui.theme.getForecastIconColor
import com.example.weather.utils.toFormattedString
import java.util.*

@ExperimentalMaterialApi
@Composable
fun ForecastRowItem(
    forecast: Forecast
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = CardBackground,
            elevation = 5.dp,
            modifier = Modifier
                .zIndex(10F),
            onClick = { isExpanded = !isExpanded }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "${forecast.temperature}º",
                        style = MaterialTheme.typography.h4,
                        color = Black
                    )
                    Text(
                        text = forecast.date.toFormattedString(),
                        color = Black
                    )
                }
                Icon(
                    painter = painterResource(
                        id = forecast.condition.getWeatherIcon()
                    ),
                    contentDescription = stringResource(id = R.string.forecast_weather_icon),
                    tint = getForecastIconColor(forecast.condition),
                    modifier = Modifier
                        .width(56.dp)
                        .height(56.dp)
                )
            }
        }

        Column (
            modifier = Modifier
                .fillMaxWidth(0.95F)
                .animateContentSize(
                    animationSpec = spring()
                )
        ) {
            if(isExpanded) {
                Column(
                    modifier = Modifier
                        .background(CardBackground)
                        .padding(
                            horizontal = 24.dp,
                            vertical = 18.dp
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = stringResource(id = R.string.humidity),
                            color = Black,
                            modifier = Modifier
                                .weight(3F)
                                .alpha(ContentAlpha.medium)
                        )
                        Text(
                            text = "${forecast.humidity}%",
                            color = Black,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.weight(1F)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.chance_of_precipitation),
                            color = Black,
                            modifier = Modifier
                                .weight(3F)
                                .alpha(ContentAlpha.medium)
                        )
                        Text(
                            text = "${forecast.precipitation}%",
                            color = Black,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.weight(1F)
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun ForecastRowItemPreview() {
    ForecastRowItem(
        forecast = Forecast(
            condition = Condition.RAINY,
            temperature = 15,
            date = Calendar.getInstance(),
            humidity = 90,
            precipitation = 100
        )
    )
}

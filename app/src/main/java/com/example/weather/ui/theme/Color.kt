package com.example.weather.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.weather.domain.entities.Condition

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Black = Color(0xFF1B1B1B)
val CardBackground = Color(0xFFDDDDDD)


val Colors.searchBoxBackground: Color
    @Composable
    get() = if(isLight) Color.White else Black

val Colors.searchBoxContent: Color
    @Composable
    get() = if(isLight) Black else Color.White

val Colors.searchPageBackground: Color
    @Composable
    get() = if(isLight) Color(0xFFF3F0F3) else MaterialTheme.colors.background

// ***

val SunnyText = Black
val SunnyPrimary = Color(0xFFEAE4CA)
val SunnySecondary = Color(0xFFF3ECD9)
val SunnyIcon = Color(0xFFEB534E)

val RainyText = Color.White
val RainyPrimary = Color(0xFF233947)
val RainySecondary = Color(0xFF2C4856)
val RainyIcon = Color(0xFF8FAFC6)

val CloudyText = Color(0xFF233947)
val CloudyPrimary = Color(0xFFD4D9D3)
val CloudySecondary = Color(0xFFDDDFDC)
val CloudyIcon = Color(0xFF9A5859)

val StormyText = Color.White
val StormyPrimary = Black
val StormySecondary = Color(0xFF292A2C)
val StormyIcon = Color(0xFF995758)

val WindyText = Color.White
val WindyPrimary = Color(0xFF9A5859)
val WindySecondary = Color(0xFFAA6367)
val WindyIcon = Color(0xFFEAE3C9)

val SnowyText = Color(0xFF233947)
val SnowyPrimary = Color(0xFF8FAFC6)
val SnowySecondary = Color(0xFF9BBED2)
val SnowyIcon = Color(0xFFFEEFE2)


// ***

data class WeatherColors(
    val text: Color,
    val primary: Color,
    val secondary: Color,
    val icon: Color
)

fun getWeatherColors(condition: Condition): WeatherColors {
    return when(condition) {
        Condition.SUNNY -> WeatherColors(
            text = SunnyText,
            primary = SunnyPrimary,
            secondary = SunnySecondary,
            icon = SunnyIcon
        )
        Condition.RAINY -> WeatherColors(
            text = RainyText,
            primary = RainyPrimary,
            secondary = RainySecondary,
            icon = RainyIcon
        )
        Condition.CLOUDY -> WeatherColors(
            text = CloudyText,
            primary = CloudyPrimary,
            secondary = CloudySecondary,
            icon = CloudyIcon
        )
        Condition.STORMY -> WeatherColors(
            text = StormyText,
            primary = StormyPrimary,
            secondary = StormySecondary,
            icon = StormyIcon
        )
        Condition.WINDY -> WeatherColors(
            text = WindyText,
            primary = WindyPrimary,
            secondary = WindySecondary,
            icon = WindyIcon
        )
        Condition.SNOWY -> WeatherColors(
            text = SnowyText,
            primary = SnowyPrimary,
            secondary = SnowySecondary,
            icon = SnowyIcon
        )
    }
}

fun getForecastIconColor (
    condition: Condition
): Color {
    return when(condition) {
        Condition.SUNNY -> SunnyIcon
        Condition.RAINY -> Color(0xFF46A3E6)
        Condition.CLOUDY -> Color(0xFF313131)
        Condition.STORMY -> Color(0xFFE0A33A)
        Condition.WINDY -> Color(0xFF646464)
        Condition.SNOWY -> Color(0xFF6259E7)
    }
}

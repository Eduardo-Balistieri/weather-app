package com.example.weather.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.weather.R
import java.util.*

@Composable
fun Calendar.toFormattedString(): String {
    val weekDay = stringResource(
        when(this.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> R.string.sunday
            Calendar.MONDAY -> R.string.monday
            Calendar.TUESDAY -> R.string.tuesday
            Calendar.WEDNESDAY -> R.string.wednesday
            Calendar.THURSDAY -> R.string.thursday
            Calendar.FRIDAY -> R.string.friday
            Calendar.SATURDAY -> R.string.saturday
            else -> R.string.unknown
        }
    )
    val monthDay = this.get(Calendar.DAY_OF_MONTH)
    val month = stringResource(
        when(this.get(Calendar.MONTH)) {
            Calendar.JANUARY -> R.string.january
            Calendar.FEBRUARY -> R.string.february
            Calendar.MARCH -> R.string.march
            Calendar.APRIL -> R.string.april
            Calendar.MAY -> R.string.may
            Calendar.JUNE -> R.string.june
            Calendar.JULY -> R.string.july
            Calendar.AUGUST -> R.string.august
            Calendar.SEPTEMBER -> R.string.september
            Calendar.OCTOBER -> R.string.october
            Calendar.NOVEMBER -> R.string.november
            Calendar.DECEMBER -> R.string.december
            else -> R.string.unknown
        }
    )
    return "$weekDay, $monthDay $month"
}

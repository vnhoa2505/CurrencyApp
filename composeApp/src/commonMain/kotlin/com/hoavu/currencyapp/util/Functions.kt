package com.hoavu.currencyapp.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import currencyapp.composeapp.generated.resources.Res
import currencyapp.composeapp.generated.resources.worksans_regular
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.Font
import kotlin.math.pow

fun calculateExchangeRate(source: Double, target: Double): Double {
    return (target / source).roundToDecimalPlaces()
}

fun convert(amount: Double, exchangeRate: Double): Double {
    return (amount * exchangeRate).roundToDecimalPlaces()
}

fun Double.roundToDecimalPlaces(decimalPlaces: Int = 6): Double {
    val factor = 10.0.pow(decimalPlaces)
    return (this * factor).toLong() / factor
}

fun displayCurrentDateTime(): String {
    val currentTimestamp = Clock.System.now()
    val date = currentTimestamp.toLocalDateTime(TimeZone.currentSystemDefault())

    // Format the LocalDate into the desired representation
    val dayOfMonth = date.dayOfMonth
    val month =
        date.month.toString().lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    val year = date.year

    // Determine the suffix for the day of the month
    val suffix = when {
        dayOfMonth in 11..13 -> "th" // Special case for 11th, 12th, and 13th
        dayOfMonth % 10 == 1 -> "st"
        dayOfMonth % 10 == 2 -> "nd"
        dayOfMonth % 10 == 3 -> "rd"
        else -> "th"
    }

    // Format the date in the desired representation
    return "$dayOfMonth$suffix $month, $year."
}

@Composable
fun GetFontFamily() = FontFamily(Font(resource = Res.font.worksans_regular))
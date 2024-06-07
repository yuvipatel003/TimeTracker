package com.appsdeviser.timetrackerpro.android.ui.core

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

fun formatDate(date: Date, format: String): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(date)
}

fun String.displayAsDate(format: String): String {
    if (this.isEmpty()) {
        return this
    }
    val formatter = SimpleDateFormat(DEFAULT_DB_DATE_FORMAT, Locale.getDefault())
    val date = formatter.parse(this)
    val outputFormat =
        SimpleDateFormat(format.ifEmpty { DEFAULT_DB_DATE_FORMAT }, Locale.getDefault())
    return date?.let { outputFormat.format(it) } ?: this
}

fun String.displayAsTime(is12HourFormat: Boolean): String {
    if (this.isEmpty()) {
        return this
    }
    val inputFormat = SimpleDateFormat(DEFAULT_DB_TIME_FORMAT, Locale.getDefault())
    val time = inputFormat.parse(this)
    val outputFormat = if (is12HourFormat) {
        SimpleDateFormat(HOUR_FORMAT_12, Locale.getDefault())
    } else {
        SimpleDateFormat(HOUR_FORMAT_24, Locale.getDefault())
    }
    return time?.let { outputFormat.format(it) } ?: this
}

fun calculateHours(startDate: String, startTime: String, endDate: String, endTime: String): Double {
    if (startDate.isEmpty() || startTime.isEmpty() || endDate.isEmpty() || endTime.isEmpty()) {
        return 0.0
    }
    val dateFormat =
        SimpleDateFormat("$DEFAULT_DB_DATE_FORMAT $DEFAULT_DB_TIME_FORMAT", Locale.getDefault())
    val startDateTime = dateFormat.parse("$startDate $startTime")
    val endDateTime = dateFormat.parse("$endDate $endTime")

    val diffInMilliseconds = endDateTime.time - startDateTime.time
    val diffInHours = diffInMilliseconds.toDouble() / (1000 * 60 * 60)

    val roundedResult = roundToNearestQuarter(diffInHours)
    return roundedResult.roundToTwoDecimal()
}

fun Double.roundToTwoDecimal(): Double {
    return DecimalFormat("#.##").format(this).toDouble()
}

fun roundToNearestQuarter(value: Double): Double {
    return (value * 4).roundToInt() / 4.0
}

val listOfDateFormat = listOf(
    "yyyy-MM-dd",
    "dd-MM-yyyy",
    "dd MMM yyyy",
    "EEE, MMM dd, yyyy",
    "EEE, MMM dd",
    "MMM dd",
)

const val DEFAULT_DB_DATE_FORMAT = "yyyy-MM-dd"
const val DEFAULT_DB_TIME_FORMAT = "HH:mm:ss"

const val HOUR_FORMAT_12: String = "hh:mm a"
const val HOUR_FORMAT_24: String = "HH:mm:ss"

const val ONE_DAY_MILLIS = 86400000

//"2024-02-25".displayAsDate("EEE, MMM dd, yyyy") + ", " +"16:45:25".displayAsTime(true),


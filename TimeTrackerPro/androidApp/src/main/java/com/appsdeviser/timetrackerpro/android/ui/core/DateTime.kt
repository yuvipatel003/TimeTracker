package com.appsdeviser.timetrackerpro.android.ui.core

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Date, format: String): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(date)
}

fun String.displayAsDate(format: String): String {
    val formatter = SimpleDateFormat(DEFAULT_DB_DATE_FORMAT, Locale.getDefault())
    val date = formatter.parse(this)
    val outputFormat = SimpleDateFormat(format, Locale.getDefault())
    return outputFormat.format(date)
}

fun String.displayAsTime(is12HourFormat: Boolean): String {
    val inputFormat = SimpleDateFormat(DEFAULT_DB_TIME_FORMAT, Locale.getDefault())
    val time = inputFormat.parse(this)
    val outputFormat = if (is12HourFormat) {
        SimpleDateFormat(HOUR_FORMAT_12, Locale.getDefault())
    } else {
        SimpleDateFormat(HOUR_FORMAT_24, Locale.getDefault())
    }
    return time?.let { outputFormat.format(it) } ?: this
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
const val HOUR_FORMAT_24: String = "HH:mm"

//"2024-02-25".displayAsDate("EEE, MMM dd, yyyy") + ", " +"16:45:25".displayAsTime(true),


package com.appsdeviser.timetrackerpro.android.ui.core

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Date, format: String): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(date)
}

fun String.displayAsDate(format: String): String {
    val formatter = SimpleDateFormat(listOfDateFormat.first(), Locale.getDefault())
    val date = formatter.parse(this)
    val outputFormat = SimpleDateFormat(format, Locale.getDefault())
    return outputFormat.format(date)
}

fun String.displayAsTime(is12HourFormat: Boolean): String {
    val format: String = if(is12HourFormat) {
        HOUR_FORMAT_12
    } else {
        HOUR_FORMAT_24
    }
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    val time = formatter.parse(this)
    val outputFormat = SimpleDateFormat(format, Locale.getDefault())
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

const val HOUR_FORMAT_12: String = "hh:mm a"
const val HOUR_FORMAT_24: String = "HH:mm"
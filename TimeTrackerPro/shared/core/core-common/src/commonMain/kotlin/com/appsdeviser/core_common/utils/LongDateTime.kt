package com.appsdeviser.core_common.utils

fun Long.toDateString(): String {
    val year = this / 10000
    val month = (this / 100) % 100
    val day = this % 100
    return "${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}"
}

fun Long.toTimeString(): String {
    val hours = this / 10000
    val minutes = (this / 100) % 100
    val seconds = this % 100
    return "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
}

fun String.toDateLong(): Long {
    val parts = this.split("-")
    val year = parts[0].toInt()
    val month = parts[1].toInt()
    val day = parts[2].toInt()
    return (year * 10000 + month * 100 + day).toLong()
}

fun String.toTimeLong(): Long {
    val parts = this.split(":")
    val hours = parts[0].toInt()
    val minutes = parts[1].toInt()
    val seconds = parts[2].toInt()
    return (hours * 10000 + minutes * 100 + seconds).toLong()
}
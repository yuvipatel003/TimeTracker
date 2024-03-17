package com.appsdeviser.core_common.utils

fun String.getAppVersionToInt(): Int {
    val newString = this.replace(".","")
    return newString.toInt()
}
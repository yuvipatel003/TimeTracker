package com.appsdeviser.core_common.utils

fun String.getAppVersionToInt(): Int {
    val newString = this.replace(".", "")
    return newString.toInt()
}

fun String.isNewVersionInstalled(previousVersion: Int): Boolean {
    return this.getAppVersionToInt() > previousVersion
}

fun String.isValidEmail(): Boolean {
    val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")
    return emailRegex.matches(this)
}

fun isValidUsername(username: String): Boolean {
    val alphanumericRegex = Regex("^[a-zA-Z0-9]{4,100}$")

    return alphanumericRegex.matches(username)
}
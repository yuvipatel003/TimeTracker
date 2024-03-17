package com.appsdeviser

interface AppConfig {
    val applicationVersion: String
    val appVersionCode: Int?
    val platformAndroid: Boolean
}
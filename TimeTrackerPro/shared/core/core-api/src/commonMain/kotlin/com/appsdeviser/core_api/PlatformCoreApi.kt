package com.appsdeviser.core_api

interface PlatformCoreApi {
    val name: String
}

expect fun getPlatformCoreApi(): PlatformCoreApi
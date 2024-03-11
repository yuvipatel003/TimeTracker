package com.appsdeviser.core_api

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.appsdeviser.timetrackerpro

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
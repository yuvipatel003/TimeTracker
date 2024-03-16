package com.appsdeviser.core_db

interface PlatformCoreDb {
    val name: String
}

expect fun getPlatformCoreDb(): PlatformCoreDb
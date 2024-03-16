package com.appsdeviser.core_common

interface PlatformCoreCommon {
    val name: String
}

expect fun getPlatformCoreCommon(): PlatformCoreCommon
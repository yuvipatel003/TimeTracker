package com.appsdeviser.core_common

class GreetingCoreCommon {
    private val platform: PlatformCoreCommon = getPlatformCoreCommon()

    fun greet(): String {
        return "Greeting Core-Common, ${platform.name}!"
    }
}
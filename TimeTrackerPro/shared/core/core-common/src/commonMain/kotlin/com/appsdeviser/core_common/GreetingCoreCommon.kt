package com.appsdeviser.core_common

class GreetingCoreCommon {
    private val platformCoreCommon: PlatformCoreCommon = getPlatformCoreCommon()

    fun greet(): String {
        return "Greeting Core Common, ${platformCoreCommon.name}!"
    }
}
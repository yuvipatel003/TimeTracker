package com.appsdeviser.core_api

class GreetingCoreApi {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Greeting Core Api, ${platform.name}!"
    }
}
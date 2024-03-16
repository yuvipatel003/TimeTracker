package com.appsdeviser.core_api

class GreetingCoreApi {
    private val platformCoreApi: PlatformCoreApi = getPlatformCoreApi()

    fun greet(): String {
        return "Core Api, ${platformCoreApi.name}!"
    }
}
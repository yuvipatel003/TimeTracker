package com.appsdeviser.timetrackerpro

import com.appsdeviser.core_api.GreetingCoreApi
import com.appsdeviser.core_common.GreetingCoreCommon

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}! \n" + GreetingCoreCommon().greet() +
                "\n" + GreetingCoreApi().greet()
    }
}
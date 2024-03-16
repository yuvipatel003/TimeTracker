package com.appsdeviser.timetrackerpro

import com.appsdeviser.core_api.GreetingCoreApi
import com.appsdeviser.core_common.GreetingCoreCommon
import com.appsdeviser.core_db.GreetingCoreDb

class Greeting {
    private val platform: Platform = getPlatform()
    private val greetingCoreCommon = GreetingCoreCommon()
    private val greetingCoreApi = GreetingCoreApi()
    private val greetingCoreDb = GreetingCoreDb()

    fun greet(): String {
        return "Hello, ${platform.name}!" +
                "\n" + greetingCoreCommon.greet() +
                "\n" + greetingCoreApi.greet() +
                "\n" + greetingCoreDb.greet()

    }
}
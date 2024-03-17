package com.appsdeviser.timetrackerpro

import com.appsdeviser.core_api.GreetingCoreApi
import com.appsdeviser.core_db.GreetingCoreDb
import com.appsdeviser.onboarding.presentation.splash.SplashViewModel

class Greeting {
    private val platform: Platform = getPlatform()
    private val greetingCoreApi = GreetingCoreApi()
    private val greetingCoreDb = GreetingCoreDb()

    fun greet(): String {
        return "Hello, ${platform.name}!" +
                "\n" + greetingCoreApi.greet() +
                "\n" + greetingCoreDb.greet()

    }
}
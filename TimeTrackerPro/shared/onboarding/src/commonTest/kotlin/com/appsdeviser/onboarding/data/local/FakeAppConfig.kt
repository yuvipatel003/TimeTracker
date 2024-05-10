package com.appsdeviser.onboarding.data.local

import com.appsdeviser.AppConfig

class FakeAppConfig : AppConfig {
    override val applicationVersion: String
        get() = "2"
    override val appVersionCode: Int?
        get() = 4
    override val isAndroidPlatform: Boolean
        get() = true
}
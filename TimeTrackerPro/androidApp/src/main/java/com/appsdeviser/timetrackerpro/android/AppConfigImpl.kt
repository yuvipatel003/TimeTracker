package com.appsdeviser.timetrackerpro.android

import com.appsdeviser.AppConfig

class AppConfigImpl: AppConfig {
    override val applicationVersion: String
        get() = BuildConfig.VERSION_NAME
    override val appVersionCode: Int?
        get() = BuildConfig.VERSION_CODE
    override val isAndroidPlatform: Boolean
        get() = true
}
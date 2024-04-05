package com.appsdeviser.timetrackerpro.di

import com.appsdeviser.AppConfig
import platform.Foundation.NSBundle

class IosAppConfigImpl: AppConfig {
    override val applicationVersion: String
        get() = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleShortVersionString").toString()
    override val appVersionCode: Int?
        get() = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleVersion").toString().toInt()
    override val isAndroidPlatform: Boolean
        get() = false
}
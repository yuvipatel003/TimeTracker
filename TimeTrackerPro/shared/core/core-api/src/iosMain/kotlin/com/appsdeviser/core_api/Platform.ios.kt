package com.appsdeviser.core_api

import platform.UIKit.UIDevice

class IOSPlatformCoreApi : PlatformCoreApi {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatformCoreApi(): PlatformCoreApi = IOSPlatformCoreApi()
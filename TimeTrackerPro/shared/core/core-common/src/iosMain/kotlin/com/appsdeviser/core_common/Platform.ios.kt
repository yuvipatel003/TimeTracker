package com.appsdeviser.core_common

import platform.UIKit.UIDevice

class IOSPlatform: PlatformCoreCommon {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatformCoreCommon(): Platform = IOSPlatform()
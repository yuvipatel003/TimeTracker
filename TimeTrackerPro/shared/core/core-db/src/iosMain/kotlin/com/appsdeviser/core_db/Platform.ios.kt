package com.appsdeviser.core_db

import platform.UIKit.UIDevice

class IOSPlatformCoreDb: PlatformCoreDb {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatformCoreDb(): PlatformCoreDb = IOSPlatformCoreDb()
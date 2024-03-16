package com.appsdeviser.core_common

class AndroidPlatform : PlatformCoreCommon {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatformCoreCommon(): PlatformCoreCommon = AndroidPlatform()
package com.appsdeviser.core_api

class AndroidPlatformCoreApi : PlatformCoreApi {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatformCoreApi(): PlatformCoreApi = AndroidPlatformCoreApi()
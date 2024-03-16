package com.appsdeviser.core_db

class AndroidPlatformCoreDb : PlatformCoreDb {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatformCoreDb(): PlatformCoreDb = AndroidPlatformCoreDb()
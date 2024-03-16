package com.appsdeviser.core_db

class GreetingCoreDb {
    private val platformCoreDb: PlatformCoreDb = getPlatformCoreDb()

    fun greet(): String {
        return "Core Db, ${platformCoreDb.name}!"
    }
}
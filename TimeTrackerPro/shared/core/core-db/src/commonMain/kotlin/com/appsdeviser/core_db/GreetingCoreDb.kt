package com.appsdeviser.core_db

import com.appsdeviser.core_common.GreetingCoreCommon

class GreetingCoreDb {
    private val platformCoreDb: PlatformCoreDb = getPlatformCoreDb()
    private val greetingCoreCommon = GreetingCoreCommon()

    fun greet(): String {
        return "Core Db, ${platformCoreDb.name}!" +
                "\n" + greetingCoreCommon.greet()
    }
}
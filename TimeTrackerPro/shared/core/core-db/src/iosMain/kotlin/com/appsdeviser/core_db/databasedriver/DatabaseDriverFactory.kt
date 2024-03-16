package com.appsdeviser.core_db.databasedriver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.appsdeviser.core_db.sqldelight.TimeTrackerDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
       return NativeSqliteDriver(TimeTrackerDatabase.Schema, "timetracker.db")
    }
}
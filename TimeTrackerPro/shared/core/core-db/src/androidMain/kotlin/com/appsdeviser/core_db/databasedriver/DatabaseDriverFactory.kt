package com.appsdeviser.core_db.databasedriver

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.appsdeviser.core_db.sqldelight.TimeTrackerDatabase

actual class DatabaseDriverFactory (
    private val context: Context
){
    actual fun create(): SqlDriver {
       return AndroidSqliteDriver(TimeTrackerDatabase.Schema, context, "timetracker.db")
    }
}
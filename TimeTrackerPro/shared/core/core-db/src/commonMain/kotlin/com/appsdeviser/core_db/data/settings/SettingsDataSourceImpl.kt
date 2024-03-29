package com.appsdeviser.core_db.data.settings

import app.cash.sqldelight.coroutines.asFlow
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.domain.settings.SettingsItem
import com.appsdeviser.core_db.flows.CommonFlow
import com.appsdeviser.core_db.flows.toCommonFlow
import com.appsdeviser.core_db.sqldelight.TimeTrackerDatabase
import kotlinx.coroutines.flow.map

class SettingsDataSourceImpl(
    db: TimeTrackerDatabase
) : SettingsDataSource {

    private val queries = db.timetrackerQueries

    override fun getSettings(): CommonFlow<SettingsItem> {
        return queries
            .getSettings()
            .asFlow()
            .map {
                return@map if (it.executeAsList().isEmpty()) {
                    val item = SettingsItem(0, "", "", "0.0.0")
                    setSettings(item)
                    item
                } else {
                    it.executeAsList().map { singleSettings ->
                        singleSettings.toSettingsItem()
                    }.first()
                }
            }.toCommonFlow()
    }

    override suspend fun setSettings(item: SettingsItem) {
        queries.insertSettings(
            username = item.userName,
            email = item.email,
            currentAppVersion = item.currentAppVersion
        )
    }

    override suspend fun clearSettings() {
        queries.clearSettings()
    }
}
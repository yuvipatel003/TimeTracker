package com.appsdeviser.core_db.domain.settings

import com.appsdeviser.core_db.flows.CommonFlow

interface SettingsDataSource {

    fun getSettings(): CommonFlow<SettingsItem>

    suspend fun setSettings(item: SettingsItem)

    suspend fun clearSettings()
}
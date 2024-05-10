package com.appsdeviser.onboarding.data.local

import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.domain.settings.SettingsItem
import com.appsdeviser.core_db.flows.CommonFlow
import com.appsdeviser.core_db.flows.toCommonFlow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeSettingsDataSource : SettingsDataSource {
    private val _data = MutableStateFlow<SettingsItem>(SettingsItem(
        id = null,
        userName = "",
        email = "",
        currentAppVersion = ""
    ))

    override fun getSettings(): CommonFlow<SettingsItem> {
        return _data.toCommonFlow()
    }

    override suspend fun setSettings(item: SettingsItem) {
        _data.value = item
    }

    override suspend fun clearSettings() {
        _data.value = SettingsItem(
            id = null,
            userName = "",
            email = "",
            currentAppVersion = ""
        )
    }
}
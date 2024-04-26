package com.appsdeviser.tracker.presentation.settings

import com.appsdeviser.core_common.utils.error.Error
import com.appsdeviser.core_db.domain.settings.SettingsItem

data class SettingsState(
    val settingsItem: SettingsItem? = null,
    val event: SettingsEvent? = null,
    val error: Error? = null
)
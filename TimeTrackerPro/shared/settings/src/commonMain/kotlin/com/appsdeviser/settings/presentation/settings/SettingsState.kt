package com.appsdeviser.settings.presentation.settings

import com.appsdeviser.core_common.utils.error.Error
import com.appsdeviser.core_db.domain.settings.SettingsItem
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem

data class SettingsState(
    val settingsItem: SettingsItem? = null,
    val showRecordPageSettingItem: ShowRecordPageSettingItem? = null,
    val event: SettingsEvent? = null,
    val error: Error? = null
)
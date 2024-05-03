package com.appsdeviser.settings.presentation.settings

import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem

sealed class SettingsEvent {
    data class OnUpdateUsername(val username: String) : SettingsEvent()
    data class OnUpdateEmail(val email: String) : SettingsEvent()
    data class OnUpdateRecordSetting(val showRecordPageSettingItem: ShowRecordPageSettingItem) :
        SettingsEvent()

    data object OnErrorSeen : SettingsEvent()
}
package com.appsdeviser.core_db.data.settings

import com.appsdeviser.core_db.domain.settings.SettingsItem
import database.SettingsEntity

fun SettingsEntity.toSettingsItem(): SettingsItem {
    return SettingsItem(
        id = this.id,
        userName = this.username,
        email = this.email,
        showOnboarding = this.showOnboarding.equals(1)
    )
}
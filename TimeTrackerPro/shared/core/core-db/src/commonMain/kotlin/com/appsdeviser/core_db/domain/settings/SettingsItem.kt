package com.appsdeviser.core_db.domain.settings

data class SettingsItem(
    val id: Long?,
    val userName: String,
    val email: String,
    val currentAppVersion: String
)

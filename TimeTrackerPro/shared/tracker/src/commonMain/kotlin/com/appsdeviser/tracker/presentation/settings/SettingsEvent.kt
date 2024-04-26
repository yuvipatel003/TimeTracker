package com.appsdeviser.tracker.presentation.settings

sealed class SettingsEvent {
    data class OnUpdateUsername(val username: String) : SettingsEvent()
    data class OnUpdateEmail(val email: String) : SettingsEvent()
    data object OnErrorSeen : SettingsEvent()
}
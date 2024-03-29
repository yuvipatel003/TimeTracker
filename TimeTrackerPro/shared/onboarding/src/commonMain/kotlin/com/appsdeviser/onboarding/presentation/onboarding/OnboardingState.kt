package com.appsdeviser.onboarding.presentation.onboarding

import com.appsdeviser.core_common.utils.error.Error
import com.appsdeviser.core_db.domain.settings.SettingsItem

data class OnboardingState(
    val userName: String = "",
    val email: String = "",
    val settingsItem: SettingsItem? = null,
    val event: OnboardingEvent? = null,
    val error: Error? = null
)

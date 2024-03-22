package com.appsdeviser.onboarding.presentation.splash

import com.appsdeviser.core_common.utils.error.Error
import com.appsdeviser.core_db.domain.feature.FeatureItem

data class SplashState(
    val username: String? = null,
    val email: String = "",
    val showOnboarding: Boolean = true,
    val showWhatsNew: Boolean = false,
    val listOfFeatures: List<FeatureItem> = emptyList(),
    val isLoading: Boolean = true,
    val error: Error? = null,
    val event: SplashEvent? = null
)

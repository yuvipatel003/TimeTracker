package com.appsdeviser.onboarding.presentation.splash

import com.appsdeviser.core_common.utils.ApiError
import com.appsdeviser.core_db.domain.feature.FeatureItem

data class SplashState(
    val username: String? = null,
    val email: String = "",
    val showOnboarding: Boolean = true,
    val listOfFeatures: List<FeatureItem> = emptyList(),
    val isLoading: Boolean = true,
    val error: ApiError? = null
)

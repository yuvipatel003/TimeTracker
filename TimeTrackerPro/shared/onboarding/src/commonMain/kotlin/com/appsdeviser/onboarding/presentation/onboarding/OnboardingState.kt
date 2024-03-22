package com.appsdeviser.onboarding.presentation.onboarding

import com.appsdeviser.core_common.utils.error.Error

data class OnboardingState(
    val list: List<OnboardingScreens> = listOfOnboarding,
    val event: OnboardingEvent? = null,
    val featureString: String = "",
    val error: Error? = null
)

sealed class OnboardingScreens {
    data object Username : OnboardingScreens()
    data object Email : OnboardingScreens()
}

val listOfOnboarding = listOf(
    OnboardingScreens.Username,
    OnboardingScreens.Email
)

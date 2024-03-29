package com.appsdeviser.onboarding.presentation.onboarding

sealed class OnboardingEvent {
    data object OnSkip : OnboardingEvent()
    data object OnNext : OnboardingEvent()
    data object OnFinish : OnboardingEvent()
    data object OnErrorSeen : OnboardingEvent()
    data class OnUserNameUpdate(val username: String) : OnboardingEvent()
    data class OnEmailUpdate(val email: String) : OnboardingEvent()
}
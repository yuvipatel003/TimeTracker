package com.appsdeviser.onboarding.presentation.onboarding

sealed class OnboardingEvent {
    data object onBack : OnboardingEvent() // Will be implemented later
    data object onFinish : OnboardingEvent()
    data class onNext(val onboardingSingleScreenState: OnboardingEvent) : OnboardingEvent()
}
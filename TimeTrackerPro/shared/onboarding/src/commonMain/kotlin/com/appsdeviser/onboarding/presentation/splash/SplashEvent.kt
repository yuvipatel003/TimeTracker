package com.appsdeviser.onboarding.presentation.splash

sealed class SplashEvent {
    data object GoToOnboarding: SplashEvent()
    data object GoToWhatsNew: SplashEvent()
    data object GoToHomePage: SplashEvent()
}

package com.appsdeviser.onboarding.presentation.splash

sealed class SplashEvent {
    data object OnStartUp: SplashEvent()
    data object GoToOnboarding: SplashEvent()
    data object GoToWhatsNew: SplashEvent() // Will be implemented later
    data object GoToHomePage: SplashEvent()
}

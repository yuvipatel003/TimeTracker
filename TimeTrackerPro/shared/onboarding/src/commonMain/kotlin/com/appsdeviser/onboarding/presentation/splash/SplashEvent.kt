package com.appsdeviser.onboarding.presentation.splash

import com.appsdeviser.core_common.utils.error.Error

sealed class SplashEvent {
    data object OnStartUp : SplashEvent()
    data object GoToOnboarding : SplashEvent()
    data object GoToWhatsNew : SplashEvent() // Will be implemented later
    data object GoToHomePage : SplashEvent()
    data class OnErrorSeen(val error: Error?) : SplashEvent()
}

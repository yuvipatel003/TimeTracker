package com.appsdeviser.onboarding.presentation.whatsnew

sealed class WhatsNewEvent {
    data object OnNext : WhatsNewEvent()
    data object OnPrevious : WhatsNewEvent()
    data object OnFinish : WhatsNewEvent()
}
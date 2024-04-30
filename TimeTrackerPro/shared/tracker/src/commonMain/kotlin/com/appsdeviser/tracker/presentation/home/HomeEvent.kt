package com.appsdeviser.tracker.presentation.home

sealed class HomeEvent {
    data object OnErrorSeen : HomeEvent()
    data object OnNotificationClick : HomeEvent()
    data object OnNextNotification : HomeEvent()
    data object OnPreviousNotification : HomeEvent()
    data object ShowSetting : HomeEvent()
    data object ShowRecords : HomeEvent()
    data object ShowCategory : HomeEvent()
    data object ShowAddNewRecord : HomeEvent()
    data object StartOrStopRecord : HomeEvent()
}
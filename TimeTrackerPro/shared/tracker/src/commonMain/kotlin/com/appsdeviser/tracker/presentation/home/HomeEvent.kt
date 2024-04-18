package com.appsdeviser.tracker.presentation.home

sealed class HomeEvent {
    data object OnNotificationClick: HomeEvent()
    data object GoToSettingPage: HomeEvent()
    data object GoToRecordsPage: HomeEvent()
    data object GoToCategoryListPage: HomeEvent()
    data object OnErrorSeen: HomeEvent()
    data object AddNewRecord: HomeEvent()
    data object AddNewCategory: HomeEvent()
    data object StartOrStopRecord: HomeEvent()
}
package com.appsdeviser.tracker.presentation.home.components

data class HomeFeatureState(
    val isSettingFeatureEnabled: Boolean = false,
    val isAddCategoryFeatureEnabled: Boolean = false,
    val isWeeklyTargetFeatureEnabled: Boolean = false,
    val isNotificationFeatureEnabled: Boolean = false
)
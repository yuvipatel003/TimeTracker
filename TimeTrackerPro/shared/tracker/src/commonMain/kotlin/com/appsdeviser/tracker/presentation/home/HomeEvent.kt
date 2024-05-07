package com.appsdeviser.tracker.presentation.home

import com.appsdeviser.tracker.domain.category.CategoryItem
import com.appsdeviser.tracker.domain.record.RecordItem

sealed class HomeEvent {
    data object OnErrorSeen : HomeEvent()
    data object OnNotificationClick : HomeEvent()
    data object OnNextNotification : HomeEvent()
    data object OnPreviousNotification : HomeEvent()
    data object ShowSetting : HomeEvent()
    data object ShowRecords : HomeEvent()
    data object ShowCategory : HomeEvent()
    data object ShowAddNewRecord : HomeEvent()
    data class StartRecord(val categoryItem: CategoryItem, val startTime: String, val startDate: String) : HomeEvent()
    data class EndRecord(val recordItem: RecordItem) : HomeEvent()
    data class SelectCategory(val categoryItem: CategoryItem) : HomeEvent()
}
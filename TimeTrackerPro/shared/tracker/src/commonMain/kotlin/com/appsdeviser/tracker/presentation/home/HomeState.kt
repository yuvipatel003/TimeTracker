package com.appsdeviser.tracker.presentation.home

import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem
import com.appsdeviser.tracker.domain.category.CategoryItem
import com.appsdeviser.tracker.domain.record.RecordItem
import com.appsdeviser.tracker.presentation.home.components.NotificationState
import com.appsdeviser.tracker.presentation.home.components.notificationList

data class HomeState(
    val userName: String = "",
    val notificationState: NotificationState = (NotificationState(false, 0, notificationList)),
    val listOfRecentRecords: List<RecordItem> = emptyList(),
    val loadShowRecordSetting: ShowRecordPageSettingItem? = null,
    val isTrackerInProgress: Boolean = false,
    val startTime: String? = null,
    val event: HomeEvent? = null,
    val error: Error? = null,
    val favouriteCategory: CategoryItem? = null,
    val isSettingFeatureEnabled: Boolean = false,
    val isAddCategoryFeatureEnabled: Boolean = false,
    val isWeeklyTargetFeatureEnabled: Boolean = false,
    val isNotificationFeatureEnabled: Boolean = false
)
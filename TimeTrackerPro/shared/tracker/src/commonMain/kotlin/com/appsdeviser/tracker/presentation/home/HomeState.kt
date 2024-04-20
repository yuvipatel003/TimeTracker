package com.appsdeviser.tracker.presentation.home

import com.appsdeviser.tracker.domain.category.CategoryItem
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem
import com.appsdeviser.tracker.domain.record.RecordItem

data class HomeState(
    val userName: String = "",
    val isNotificationOpen: Boolean = false,
    val listOfRecentRecords: List<RecordItem> = emptyList(),
    val loadShowRecordSetting: ShowRecordPageSettingItem? = null,
    val isTrackerInProgress: Boolean = false,
    val startTime: String? = null,
    val event: HomeEvent? = null,
    val error: Error? = null,
    val favouriteCategory: CategoryItem? = null,
    val isSettingFeatureEnabled: Boolean = false,
    val isAddCategoryFeatureEnabled: Boolean = false,
    val isWeeklyTargetFeatureEnabled: Boolean = false
)
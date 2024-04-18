package com.appsdeviser.tracker.presentation.home

import com.appsdeviser.core_db.domain.category.CategoryItem
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem

data class HomeState(
    val userName: String = "",
    val isNotificationOpen: Boolean = false,
    val listOfRecentRecord: List<String> = emptyList(),
    val showRecordSetting: ShowRecordPageSettingItem? = null,
    val isTrackerInProgress: Boolean = false,
    val startTime: String? = null,
    val event: HomeEvent? = null,
    val error: Error? = null,
    val favouriteCategory: CategoryItem? = null
)
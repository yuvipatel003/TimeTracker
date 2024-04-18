package com.appsdeviser.tracker.presentation.home

import com.appsdeviser.core_db.domain.category.CategoryItem

data class HomeState(
    val isNotificationOpen: Boolean = false,
    val listOfRecentRecord: List<String> = emptyList(),
    val isTrackerInProgress: Boolean = false,
    val startTime: String? = null,
    val event: HomeEvent? = null,
    val error: Error? = null,
    val favouriteCategory: CategoryItem? = null
)
package com.appsdeviser.tracker.presentation.home.components

import com.appsdeviser.tracker.domain.category.CategoryItem

data class TrackerInProgressState(
    val isTrackerInProgress: Boolean = false,
    val startTime: String? = null,
    val categoryItem: CategoryItem? = null
)
package com.appsdeviser.tracker.presentation.home.components

import com.appsdeviser.tracker.domain.category.CategoryItem

data class ActiveRecordState(
    val isTrackerInProgress: Boolean = false,
    val startDate: String? = null,
    val startTime: String? = null,
    val categoryItem: CategoryItem? = null
)
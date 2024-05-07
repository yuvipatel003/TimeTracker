package com.appsdeviser.tracker.presentation.home

import com.appsdeviser.tracker.domain.category.CategoryItem
import com.appsdeviser.tracker.presentation.home.components.CategoryState
import com.appsdeviser.tracker.presentation.home.components.GoalState
import com.appsdeviser.tracker.presentation.home.components.HomeFeatureState
import com.appsdeviser.tracker.presentation.home.components.NotificationState
import com.appsdeviser.tracker.presentation.home.components.RecentRecordState
import com.appsdeviser.tracker.presentation.home.components.ActiveRecordState

data class HomeState(
    val userName: String = "",
    val notificationState: NotificationState = NotificationState(),
    val homeFeatureState: HomeFeatureState = HomeFeatureState(),
    val recentRecordState: RecentRecordState = RecentRecordState(),
    val activeRecordState: ActiveRecordState = ActiveRecordState(),
    val categoryState: CategoryState = CategoryState(),
    val selectedCategory: CategoryItem? = null,
    val goalState: GoalState = GoalState(),
    val event: HomeEvent? = null,
    val error: Error? = null
)
package com.appsdeviser.tracker.presentation.home

import com.appsdeviser.tracker.presentation.home.components.CategoryState
import com.appsdeviser.tracker.presentation.home.components.GoalState
import com.appsdeviser.tracker.presentation.home.components.HomeFeatureState
import com.appsdeviser.tracker.presentation.home.components.NotificationState
import com.appsdeviser.tracker.presentation.home.components.RecentRecordState
import com.appsdeviser.tracker.presentation.home.components.TrackerInProgressState

data class HomeState(
    val userName: String = "",
    val notificationState: NotificationState = NotificationState(),
    val homeFeatureState: HomeFeatureState = HomeFeatureState(),
    val recentRecordState: RecentRecordState = RecentRecordState(),
    val trackerInProgressState: TrackerInProgressState = TrackerInProgressState(),
    val categoryState: CategoryState = CategoryState(),
    val goalState: GoalState = GoalState(),
    val event: HomeEvent? = null,
    val error: Error? = null
)
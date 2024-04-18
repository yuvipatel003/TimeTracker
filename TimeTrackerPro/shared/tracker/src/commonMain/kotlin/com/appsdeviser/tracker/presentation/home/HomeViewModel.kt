package com.appsdeviser.tracker.presentation.home

import com.appsdeviser.core_db.domain.category.CategoryDataSource
import com.appsdeviser.core_db.domain.feature.FeatureDataSource
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import kotlinx.coroutines.CoroutineScope

class HomeViewModel (
    private val featureDataSource: FeatureDataSource,
    private val settingsDataSource: SettingsDataSource,
    private val categoryDataSource: CategoryDataSource,
    coroutineScope: CoroutineScope?
) {
    
}
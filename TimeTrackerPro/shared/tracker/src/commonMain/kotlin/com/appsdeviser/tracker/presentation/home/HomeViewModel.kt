package com.appsdeviser.tracker.presentation.home

import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.core_db.domain.feature.FeatureDataSource
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import kotlinx.coroutines.CoroutineScope

class HomeViewModel (
    private val featureDataSource: FeatureDataSource,
    private val settingsDataSource: SettingsDataSource,
    private val categoryDataSource: CategoryDataSource,
    private val showRecordPageSettingDataSource: ShowRecordPageSettingDataSource,
    coroutineScope: CoroutineScope?
) {
    
}
package com.appsdeviser.timetrackerpro.android.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.core_db.featuremanager.FeatureManager
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.record.RecordDataSource
import com.appsdeviser.tracker.presentation.home.HomeEvent
import com.appsdeviser.tracker.presentation.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidHomeViewModel @Inject constructor(
    private val featureManager: FeatureManager,
    private val settingsDataSource: SettingsDataSource,
    private val categoryDataSource: CategoryDataSource,
    private val showRecordPageSettingDataSource: ShowRecordPageSettingDataSource,
    private val recordDataSource: RecordDataSource
): ViewModel() {

    private val viewModel by lazy {
        HomeViewModel(
            featureManager = featureManager,
            settingsDataSource = settingsDataSource,
            categoryDataSource = categoryDataSource,
            showRecordPageSettingDataSource = showRecordPageSettingDataSource,
            recordDataSource = recordDataSource,
            coroutineScope = viewModelScope

        )
    }

    val state = viewModel.state

    fun onEvent(event: HomeEvent){
        viewModel.onEvent(event)
    }
}
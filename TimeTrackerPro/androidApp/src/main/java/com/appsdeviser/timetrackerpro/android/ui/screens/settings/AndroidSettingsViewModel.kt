package com.appsdeviser.timetrackerpro.android.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.core_db.featuremanager.FeatureManager
import com.appsdeviser.settings.presentation.settings.SettingsEvent
import com.appsdeviser.settings.presentation.settings.SettingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidSettingsViewModel @Inject constructor(
    private val featureManager: FeatureManager,
    private val settingsDataSource: SettingsDataSource,
    private val showRecordPageSettingDataSource: ShowRecordPageSettingDataSource
): ViewModel() {

    private val viewModel by lazy {
        SettingsViewModel(
            featureManager = featureManager,
            settingsDataSource = settingsDataSource,
            showRecordPageSettingDataSource = showRecordPageSettingDataSource,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: SettingsEvent){
        viewModel.onEvent(event)
    }
}
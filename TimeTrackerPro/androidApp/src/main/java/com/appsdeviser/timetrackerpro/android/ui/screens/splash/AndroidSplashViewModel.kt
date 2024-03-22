package com.appsdeviser.timetrackerpro.android.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsdeviser.AppConfig
import com.appsdeviser.core_db.domain.feature.FeatureDataSource
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.onboarding.domain.features.FeaturesClient
import com.appsdeviser.onboarding.presentation.splash.SplashEvent
import com.appsdeviser.onboarding.presentation.splash.SplashViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidSplashViewModel @Inject constructor(
    private val featureDataSource: FeatureDataSource,
    private val featuresClient: FeaturesClient,
    private val settingsDataSource: SettingsDataSource,
    private val appConfig: AppConfig
): ViewModel() {

    private val viewModel by lazy {
        SplashViewModel(
            featureDataSource = featureDataSource,
            featuresClient = featuresClient,
            settingsDataSource = settingsDataSource,
            coroutineScope = viewModelScope,
            appConfig = appConfig
        )
    }

    val state = viewModel.state

    fun onEvent(event: SplashEvent) {
        viewModel.onEvent(event)
    }
}
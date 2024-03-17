package com.appsdeviser.onboarding.presentation.splash

import com.appsdeviser.core_db.domain.feature.FeatureDataSource
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.flows.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SplashViewModel(
    private val settingsDataSource: SettingsDataSource,
    private val featureDataSource: FeatureDataSource,
    private val coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(SplashState())

    val state = combine(
        _state,
        settingsDataSource.getSettings(),
        featureDataSource.getFeatures()
    ) { state, settings, features ->
        state.copy(
            username = settings.userName,
            email = settings.email,
            showOnboarding = settings.showOnboarding,
            listOfFeatures = features
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),SplashState())
        .toCommonStateFlow()
}
package com.appsdeviser.onboarding.presentation.onboarding

import com.appsdeviser.core_common.constants.FeatureKey
import com.appsdeviser.core_db.featuremanager.FeatureManager
import com.appsdeviser.core_db.flows.toCommonStateFlow
import com.appsdeviser.onboarding.presentation.splash.SplashEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class OnboardingViewModel(
    featureManager: FeatureManager,
    coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(OnboardingState())

    val state = combine(
        _state,
        featureManager.isEnabled(FeatureKey.Show_Home_Page)
    ) { state, isHomePageAvailable ->
        _state.update {
            it.copy(
                featureString = isHomePageAvailable.toString()
            )
        }
        state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), OnboardingState())
        .toCommonStateFlow()

    fun onEvent(event: SplashEvent) {
        when (event) {
            else -> Unit
        }
    }
}
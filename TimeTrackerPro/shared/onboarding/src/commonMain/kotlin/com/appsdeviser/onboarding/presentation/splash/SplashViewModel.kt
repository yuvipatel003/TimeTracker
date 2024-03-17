package com.appsdeviser.onboarding.presentation.splash

import com.appsdeviser.core_common.utils.ApiException
import com.appsdeviser.core_common.utils.Result
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.flows.toCommonStateFlow
import com.appsdeviser.onboarding.domain.features.FeaturesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val featuresUseCase: FeaturesUseCase,
    private val settingsDataSource: SettingsDataSource,
    private val coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(SplashState())
    private var featuresJob: Job? = null

    val state = combine(
        _state,
        settingsDataSource.getSettings()
    ) { state, settings ->
        state.copy(
            username = settings.userName,
            email = settings.email,
            showOnboarding = settings.showOnboarding
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),SplashState())
        .toCommonStateFlow()
    init {
        loadFeatures()
    }
    private fun loadFeatures() {
        featuresJob = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
            val result = featuresUseCase.invoke()

            when(result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = (result.throwable as? ApiException)?.error
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            listOfFeatures = result.data ?: emptyList()
                        )
                    }
                }
            }
        }
    }
}
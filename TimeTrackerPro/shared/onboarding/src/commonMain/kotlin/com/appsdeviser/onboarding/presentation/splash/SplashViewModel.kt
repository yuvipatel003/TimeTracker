package com.appsdeviser.onboarding.presentation.splash

import com.appsdeviser.AppConfig
import com.appsdeviser.core_common.utils.ApiException
import com.appsdeviser.core_common.utils.Result
import com.appsdeviser.core_common.utils.getAppVersionToInt
import com.appsdeviser.core_common.utils.isNewVersionInstalled
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
    private val coroutineScope: CoroutineScope?,
    private val appConfig: AppConfig
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(SplashState())
    private var featuresJob: Job? = null

    val state = combine(
        _state,
        settingsDataSource.getSettings()
    ) { state, settings ->
        when {
            state.username != settings.userName ||
                state.email != settings.email -> {
                state.copy(
                    username = settings.userName,
                    email = settings.email,
                    showOnboarding = settings.userName.isEmpty(),
                    showWhatsNew = false
                )
            }
            appConfig.applicationVersion.isNewVersionInstalled(
                settings.currentAppVersion.getAppVersionToInt()) -> {
                state.copy(
                    showOnboarding = false,
                    showWhatsNew = true
                )
                }
            else -> state
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SplashState())
        .toCommonStateFlow()

    fun onEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.OnStartUp -> {
                loadFeatures()
            }

            else -> {
                // Not handled here
            }
        }
    }

    init {
        _state.update {
            it.copy(
                event = SplashEvent.OnStartUp
            )
        }
    }

    private fun loadFeatures() {
        featuresJob = viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            when (val result = featuresUseCase.invoke()) {
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
                            listOfFeatures = result.data ?: emptyList(),
                            event = when {
                                it.showOnboarding -> SplashEvent.GoToOnboarding
                                it.showWhatsNew -> SplashEvent.GoToWhatsNew
                                else -> SplashEvent.GoToHomePage
                            }
                        )
                    }
                }
            }
        }
    }
}
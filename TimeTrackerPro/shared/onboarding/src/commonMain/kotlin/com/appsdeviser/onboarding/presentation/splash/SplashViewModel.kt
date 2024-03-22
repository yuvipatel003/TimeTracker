package com.appsdeviser.onboarding.presentation.splash

import com.appsdeviser.AppConfig
import com.appsdeviser.core_common.utils.Result
import com.appsdeviser.core_common.utils.getAppVersionToInt
import com.appsdeviser.core_common.utils.isNewVersionInstalled
import com.appsdeviser.core_db.domain.feature.FeatureDataSource
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.domain.settings.SettingsItem
import com.appsdeviser.core_db.featuremanager.FeatureManager
import com.appsdeviser.core_db.flows.toCommonStateFlow
import com.appsdeviser.onboarding.domain.features.FeaturesClient
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
    private val featureDataSource: FeatureDataSource,
    private val featuresClient: FeaturesClient,
    private val settingsDataSource: SettingsDataSource,
    coroutineScope: CoroutineScope?,
    private val appConfig: AppConfig,
    private val featureManager: FeatureManager
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
                settings.currentAppVersion.getAppVersionToInt()
            ) -> {
                settingsDataSource.setSettings(
                    SettingsItem(
                        id = null,
                        userName = settings.userName,
                        email = settings.email,
                        showOnboarding = settings.showOnboarding,
                        currentAppVersion = appConfig.applicationVersion
                    )
                )
                state.copy(
                    showOnboarding = false,
                    showWhatsNew = true
                )
            }

            else -> state.copy(showOnboarding = true)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SplashState())
        .toCommonStateFlow()

    fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.OnErrorSeen -> {
                when (event.error) {
                    else -> startAppCompleted()
                }
            }
            else -> Unit
        }
    }

    init {
        loadFeatures()
    }

    private fun startAppCompleted() {
        _state.update {
            it.copy(
                isLoading = false,
                event = if (it.showOnboarding) {
                    SplashEvent.GoToOnboarding
                } else if (it.showWhatsNew) {
                    SplashEvent.GoToWhatsNew
                } else {
                    SplashEvent.GoToHomePage
                }
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
            when (val result = featuresClient.getFeatures()) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.error
                        )
                    }
                }

                is Result.Success -> {
                    featureDataSource.insertFeatures(result.data.list)
                    featureManager.initialize()
                    _state.update {
                        it.copy(
                            isLoading = false,
                            listOfFeatures = result.data.list
                        )
                    }
                    startAppCompleted()
                }
            }
        }
    }
}
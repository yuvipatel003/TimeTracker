package com.appsdeviser.onboarding.presentation.onboarding

import com.appsdeviser.core_common.utils.error.UiError
import com.appsdeviser.core_common.utils.isValidEmail
import com.appsdeviser.core_common.utils.isValidUsername
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.flows.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val settingsDataSource: SettingsDataSource,
    coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(OnboardingState())

    val state = combine(
        _state,
        settingsDataSource.getSettings()
    ) { state, settingsItem ->
        _state.update {
            it.copy(
                settingsItem = settingsItem
            )
        }
        state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), OnboardingState())
        .toCommonStateFlow()

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            is OnboardingEvent.OnSkip -> {
                _state.update {
                    it.copy(
                        error = UiError.Notification.MISSING_REQUIRED_FIELD
                    )
                }
            }

            is OnboardingEvent.OnNext -> {
                validateInputsAndProceed()
            }

            is OnboardingEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }

            is OnboardingEvent.OnEmailUpdate -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }

            is OnboardingEvent.OnUserNameUpdate -> {
                _state.update {
                    it.copy(
                        userName = event.username
                    )
                }
            }

            else -> {}
        }
    }

    private fun validateInputsAndProceed() {
        val username = state.value.userName
        val email = state.value.email

        val error = when {
            username.isEmpty() || email.isEmpty() -> UiError.Notification.MISSING_REQUIRED_FIELD
            !email.isValidEmail() -> UiError.Notification.INVALID_EMAIL
            !isValidUsername(username) -> UiError.Notification.INVALID_USERNAME
            else -> null
        }

        if (error != null) {
            _state.update {
                it.copy(
                    error = error
                )
            }
        } else {
            state.value.settingsItem?.copy(
                userName = username,
                email = email
            )?.let {
                viewModelScope.launch {
                    settingsDataSource.setSettings(it)
                }
            }
            _state.update {
                it.copy(
                    event = OnboardingEvent.OnFinish
                )
            }
        }
    }
}
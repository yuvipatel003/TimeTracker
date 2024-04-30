package com.appsdeviser.settings.presentation.settings

import com.appsdeviser.core_common.utils.error.UiError
import com.appsdeviser.core_common.utils.isValidEmail
import com.appsdeviser.core_common.utils.isValidUsername
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem
import com.appsdeviser.core_db.featuremanager.FeatureManager
import com.appsdeviser.core_db.flows.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val featureManager: FeatureManager,
    private val settingsDataSource: SettingsDataSource,
    private val showRecordPageSettingDataSource: ShowRecordPageSettingDataSource,
    coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(SettingsState())

    val state = combine(
        _state,
        settingsDataSource.getSettings(),
        showRecordPageSettingDataSource.getShowRecordSetting()

    ) { state, settingsItem, recordPageSettingItem ->
        _state.update {
            it.copy(
                settingsItem = settingsItem,
                showRecordPageSettingItem = recordPageSettingItem
            )
        }
        state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SettingsState())
        .toCommonStateFlow()

    fun onEvent(event: SettingsEvent){
        when(event){
            is SettingsEvent.OnUpdateEmail -> {
                updateEmailAndUsername(email = event.email, username = state.value.settingsItem?.userName ?: "")
                _state.update {
                    it.copy(
                        event = null
                    )
                }
            }

            is SettingsEvent.OnUpdateUsername -> {
                updateEmailAndUsername(email = state.value.settingsItem?.email ?: "", username = event.username)
                _state.update {
                    it.copy(
                        event = null
                    )
                }
            }

            is SettingsEvent.OnUpdateRecordSetting -> {
                _state.update {
                    it.copy(
                        event = null,
                        showRecordPageSettingItem = event.showRecordPageSettingItem
                    )
                }
                updateRecordSetting()
            }

            is SettingsEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }
        }
    }

    private fun updateRecordSetting() {
        state.value.showRecordPageSettingItem?.let {
            viewModelScope.launch {
                showRecordPageSettingDataSource.insertShowRecordSetting(it)
            }
        }
        // Sending NotificationError to sent Toast
        _state.update {
            it.copy(
                error = UiError.Notification.UPDATED
            )
        }
    }

    private fun updateEmailAndUsername(email: String, username: String) {
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
            // Sending NotificationError to sent Toast
            _state.update {
                it.copy(
                    error = UiError.Notification.UPDATED
                )
            }
        }
    }
}
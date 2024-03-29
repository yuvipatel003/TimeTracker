package com.appsdeviser.timetrackerpro.android.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.onboarding.presentation.onboarding.OnboardingEvent
import com.appsdeviser.onboarding.presentation.onboarding.OnboardingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidOnboardingViewModel @Inject constructor(
    private val settingsDataSource: SettingsDataSource
) : ViewModel() {

    private val viewModel by lazy {
        OnboardingViewModel(
            settingsDataSource = settingsDataSource,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: OnboardingEvent) {
        viewModel.onEvent(event)
    }
}
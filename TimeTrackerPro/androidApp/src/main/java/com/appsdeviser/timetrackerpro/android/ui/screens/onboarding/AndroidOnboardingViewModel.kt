package com.appsdeviser.timetrackerpro.android.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsdeviser.core_db.featuremanager.FeatureManager
import com.appsdeviser.onboarding.presentation.onboarding.OnboardingViewModel
import com.appsdeviser.onboarding.presentation.splash.SplashEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidOnboardingViewModel @Inject constructor(
    private val featureManager: FeatureManager
) : ViewModel() {

    private val viewModel by lazy {
        OnboardingViewModel(
            featureManager = featureManager,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: SplashEvent) {
        viewModel.onEvent(event)
    }
}
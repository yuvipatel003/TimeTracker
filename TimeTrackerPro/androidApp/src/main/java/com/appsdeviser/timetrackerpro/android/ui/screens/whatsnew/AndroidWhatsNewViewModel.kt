package com.appsdeviser.timetrackerpro.android.ui.screens.whatsnew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsdeviser.onboarding.presentation.whatsnew.WhatsNewEvent
import com.appsdeviser.onboarding.presentation.whatsnew.WhatsNewViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidWhatsNewViewModel @Inject constructor(
) : ViewModel() {

    private val viewModel by lazy {
        WhatsNewViewModel(
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: WhatsNewEvent) {
        viewModel.onEvent(event)
    }
}
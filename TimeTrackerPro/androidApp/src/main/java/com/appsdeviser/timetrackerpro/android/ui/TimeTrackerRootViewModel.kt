package com.appsdeviser.timetrackerpro.android.ui

import com.appsdeviser.core_common.utils.error.Error
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


class TimeTrackerRootViewModel {
    var state = MutableStateFlow<Error?>(null)
        private set

    fun onError(error: Error) {
        state.update {
            error
        }
    }

}
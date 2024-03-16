package com.appsdeviser.core_db.flows

import kotlinx.coroutines.flow.StateFlow

actual class CommonStateFlow<T> actual constructor(
    private val stateFlow: StateFlow<T>
): StateFlow <T> by stateFlow
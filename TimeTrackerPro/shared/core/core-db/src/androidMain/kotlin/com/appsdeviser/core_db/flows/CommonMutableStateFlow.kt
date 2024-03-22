package com.appsdeviser.core_db.flows

import kotlinx.coroutines.flow.MutableStateFlow

actual class CommonMutableStateFlow<T> actual constructor(
    private val mutableStateFlow: MutableStateFlow<T>
) : MutableStateFlow<T> by mutableStateFlow
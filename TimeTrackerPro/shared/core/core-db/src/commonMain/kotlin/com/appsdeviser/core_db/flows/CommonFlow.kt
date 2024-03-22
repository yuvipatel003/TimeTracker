package com.appsdeviser.core_db.flows

import kotlinx.coroutines.flow.Flow

expect class CommonFlow<T>(flow: Flow<T>) : Flow<T>

fun <T> Flow<T>.toCommonFlow() = CommonFlow(this)
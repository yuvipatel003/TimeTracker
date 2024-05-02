package com.appsdeviser.tracker.presentation.record.view

import com.appsdeviser.core_common.utils.error.Error
import com.appsdeviser.tracker.domain.record.RecordItem

data class ViewRecordState(
    val event: ViewRecordEvent? = null,
    val error: Error? = null,
    val listOfRecords: List<RecordItem> = emptyList()
)
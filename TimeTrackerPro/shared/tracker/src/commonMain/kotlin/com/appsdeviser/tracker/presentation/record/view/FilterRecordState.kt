package com.appsdeviser.tracker.presentation.record.view

data class FilterRecordState(
    val categoryId: Long? = null,
    val isPaid: Boolean? = null,
    val startDate: String? = null,
    val endDate: String? = null
)
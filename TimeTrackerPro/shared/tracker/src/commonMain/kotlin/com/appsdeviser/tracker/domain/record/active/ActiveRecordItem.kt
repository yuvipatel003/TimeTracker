package com.appsdeviser.tracker.domain.record.active

data class ActiveRecordItem(
    val id : Long?,
    val startDate : String,
    val startTime : String,
    val categoryId : Long
)

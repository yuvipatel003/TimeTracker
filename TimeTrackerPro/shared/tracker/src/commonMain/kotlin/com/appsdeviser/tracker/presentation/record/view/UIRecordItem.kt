package com.appsdeviser.tracker.presentation.record.view

data class UIRecordItem(
    val id: Long?,
    val startDate: String,
    val startTime: String,
    val endTime: String,
    val endDate: String,
    val totalTime: String,
    val totalAmount: String,
    val categoryId: Long,
    val categoryType: String,
    val categoryName: String,
    val categoryRate: String,
    val categoryColor: String,
    val isPaid: Boolean,
    val note: String,
    val lastUpdated: String
)

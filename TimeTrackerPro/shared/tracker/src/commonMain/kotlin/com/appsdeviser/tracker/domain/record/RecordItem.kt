package com.appsdeviser.tracker.domain.record

@kotlinx.serialization.Serializable
data class RecordItem(
    val id: Long?,
    val startDate: String,
    val startTime: String,
    val endTime: String,
    val endDate: String,
    val totalTime: String,
    val totalAmount: String,
    val categoryId: Long,
    val isPaid: Boolean,
    val note: String,
    val lastUpdated: String
)

@kotlinx.serialization.Serializable
data class RecordList(
    val list: List<RecordItem>
)
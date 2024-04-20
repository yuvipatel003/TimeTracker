package com.appsdeviser.tracker.domain.record

@kotlinx.serialization.Serializable
data class RecordItem(
    val id: Long?,
    val date: String,
    val startTime: String,
    val endTime: String,
    val totalTime: String,
    val totalAmount: String,
    val categoryType: String,
    val categoryName: String,
    val rate: Long,
    val isPaid: Boolean,
    val lastUpdated: String
)

@kotlinx.serialization.Serializable
data class RecordList(
    val list: List<RecordItem>
)
package com.appsdeviser.tracker.presentation.record.add

import com.appsdeviser.tracker.domain.record.RecordItem

sealed class AddRecordEvent {
    data object OnErrorSeen : AddRecordEvent()
    data class OnSelectRecord(val recordId: Long) : AddRecordEvent()
    data class AddRecord(val recordItem: RecordItem, val lastUpdated: String) : AddRecordEvent()
    data object Reset : AddRecordEvent()
    data class OnSelectCategory(val categoryId: Long) : AddRecordEvent()
    data class UpdateDate(val date: String, val isStartDate: Boolean) : AddRecordEvent()
    data class UpdateTime(val time: String, val isStartTime: Boolean) : AddRecordEvent()
    data class UpdateTotalHour(val totalHour: String) : AddRecordEvent()
    data class UpdateTotalEarning(val totalEarning: String) : AddRecordEvent()
    data class UpdateNote(val note: String) : AddRecordEvent()
    data class Paid(val isPaid: Boolean) : AddRecordEvent()
}
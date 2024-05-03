package com.appsdeviser.tracker.presentation.record.view

sealed class ViewRecordEvent {
    data object OnErrorSeen : ViewRecordEvent()
    data class SelectRecord(val recordId: Long) : ViewRecordEvent()
}
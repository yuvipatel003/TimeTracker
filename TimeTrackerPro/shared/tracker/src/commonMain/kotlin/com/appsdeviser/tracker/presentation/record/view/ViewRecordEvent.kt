package com.appsdeviser.tracker.presentation.record.view

sealed class ViewRecordEvent {
    data object OnErrorSeen : ViewRecordEvent()
    data class SelectRecord(val recordId: Long) : ViewRecordEvent()
    data class UpdateElement(val uiRecordItem: UIRecordItem) : ViewRecordEvent()
    data class MarkElementToUpdate(val uiRecordItem: UIRecordItem) : ViewRecordEvent()
    data object LoadNextRecords : ViewRecordEvent()
    data class DeleteRecord(val uiRecordItem: UIRecordItem) : ViewRecordEvent()
}
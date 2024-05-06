package com.appsdeviser.tracker.presentation.record.view

interface FilterRecord {
    fun getRecordsBetweenDates(
        recordsList: List<UIRecordItem>,
        startDate: String,
        endDate: String
    ): List<UIRecordItem>
}
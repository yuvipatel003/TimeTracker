package com.appsdeviser.timetrackerpro.android.di

import com.appsdeviser.timetrackerpro.android.ui.core.DEFAULT_DB_DATE_FORMAT
import com.appsdeviser.timetrackerpro.android.ui.core.displayAsDate
import com.appsdeviser.tracker.presentation.record.view.FilterRecord
import com.appsdeviser.tracker.presentation.record.view.UIRecordItem

class FilterRecordImpl : FilterRecord {
    override fun getRecordsBetweenDates(
        recordsList: List<UIRecordItem>,
        startDate: String,
        endDate: String
    ): List<UIRecordItem> {
        val startDateFormatted = startDate.displayAsDate(DEFAULT_DB_DATE_FORMAT)
        val endDateFormatted = endDate.displayAsDate(DEFAULT_DB_DATE_FORMAT)

        val filteredList = recordsList.filter { recordItem ->
            val date = recordItem.startDate.displayAsDate(DEFAULT_DB_DATE_FORMAT)
            date in startDateFormatted..endDateFormatted
        }
        return filteredList
    }
}
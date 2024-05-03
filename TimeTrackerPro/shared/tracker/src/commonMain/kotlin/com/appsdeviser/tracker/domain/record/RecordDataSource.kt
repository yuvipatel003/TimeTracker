package com.appsdeviser.tracker.domain.record

import com.appsdeviser.core_db.flows.CommonFlow

interface RecordDataSource {

    fun getRecordList(offset: Long = 0): CommonFlow<List<RecordItem>>

    fun getSelectedCategoryRecordList(
        categoryId: Long,
        offset: Long = 0
    ): CommonFlow<List<RecordItem>>

    fun getRecordsBetweenDates(
        startDate: Long,
        endDate: Long,
        offset: Long = 0
    ): CommonFlow<List<RecordItem>>

    fun getRecord(id: Long): CommonFlow<RecordItem?>

    suspend fun insertRecord(item: RecordItem)

    suspend fun deleteRecord(item: RecordItem)

    suspend fun deleteSelectedCategoryRecord(categoryId: Long)

    suspend fun markRecordAsPaid(item: RecordItem)

    suspend fun markRecordAsUnPaid(item: RecordItem)

    suspend fun clearRecords()
}
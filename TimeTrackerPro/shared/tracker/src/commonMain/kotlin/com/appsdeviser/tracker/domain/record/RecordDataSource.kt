package com.appsdeviser.tracker.domain.record

import com.appsdeviser.core_db.flows.CommonFlow

interface RecordDataSource {

    fun getRecordList(id: Long = 0): CommonFlow<List<RecordItem>>

    fun getRecord(id: Long): CommonFlow<RecordItem?>

    suspend fun insertRecord(item: RecordItem)

    suspend fun deleteRecord(item: RecordItem)

    suspend fun markRecordAsPaid(item: RecordItem)

    suspend fun markRecordAsUnPaid(item: RecordItem)

    suspend fun clearRecords()
}
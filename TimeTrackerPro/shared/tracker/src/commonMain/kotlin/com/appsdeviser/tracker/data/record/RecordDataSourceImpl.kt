package com.appsdeviser.tracker.data.record

import app.cash.sqldelight.coroutines.asFlow
import com.appsdeviser.core_common.utils.toDateLong
import com.appsdeviser.core_common.utils.toLong
import com.appsdeviser.core_common.utils.toTimeLong
import com.appsdeviser.core_db.flows.CommonFlow
import com.appsdeviser.core_db.flows.toCommonFlow
import com.appsdeviser.core_db.sqldelight.TimeTrackerDatabase
import com.appsdeviser.tracker.domain.record.RecordDataSource
import com.appsdeviser.tracker.domain.record.RecordItem
import kotlinx.coroutines.flow.map

class RecordDataSourceImpl(
    db: TimeTrackerDatabase
): RecordDataSource {

    private val queries = db.timetrackerQueries

    override fun getRecordList(offset: Long): CommonFlow<List<RecordItem>> {
        return queries
            .getRecords(offset)
            .asFlow()
            .map {
                it.executeAsList().map { recordEntity ->
                    recordEntity.toRecordItem()
                }
            }.toCommonFlow()
    }

    override fun getSelectedCategoryRecordList(
        categoryId: Long,
        offset: Long
    ): CommonFlow<List<RecordItem>> {
        return queries
            .getSelectedCategoryRecords(categoryId, offset)
            .asFlow()
            .map {
                it.executeAsList().map { recordEntity ->
                    recordEntity.toRecordItem()
                }
            }.toCommonFlow()
    }

    override fun getRecordsBetweenDates(
        startDate: Long,
        endDate: Long,
        offset: Long
    ): CommonFlow<List<RecordItem>> {
        return queries
            .getRecordsBetweenDates(startDate, endDate, offset)
            .asFlow()
            .map {
                it.executeAsList().map { recordEntity ->
                    recordEntity.toRecordItem()
                }
            }.toCommonFlow()
    }

    override fun getRecord(id: Long): CommonFlow<RecordItem?> {
        return queries
            .getSingleRecord(id)
            .asFlow()
            .map {
                return@map if(it.executeAsList().isEmpty()) {
                    null
                } else {
                    it.executeAsList().map { recordEntity ->
                        recordEntity.toRecordItem()
                    }.first()
                }
            }.toCommonFlow()
    }

    override suspend fun insertRecord(item: RecordItem) {
        queries.insertRecord(
            id = item.id,
            startDate = item.startDate.toDateLong(),
            startTime = item.startTime.toTimeLong(),
            endTime = item.endTime.toTimeLong(),
            endDate = item.endDate.toDateLong(),
            totalTime = item.totalTime,
            totalAmount = item.totalAmount,
            categoryId = item.categoryId,
            isPaid = item.isPaid.toLong(),
            note = item.note,
            lastUpdated = item.lastUpdated.toDateLong()
        )
    }

    override suspend fun deleteRecord(item: RecordItem) {
       queries.deleteRecord(id = item.id ?: -1)
    }

    override suspend fun deleteSelectedCategoryRecord(categoryId: Long) {
        queries.deleteSelectedCategoryRecord(categoryId)
    }

    override suspend fun markRecordAsPaid(item: RecordItem) {
        queries.markAsPaid(id = item.id ?: -1)
    }

    override suspend fun markRecordAsUnPaid(item: RecordItem) {
        queries.markAsUnPaid(id = item.id ?: -1)
    }

    override suspend fun clearRecords() {
        queries.clearRecords()
    }
}
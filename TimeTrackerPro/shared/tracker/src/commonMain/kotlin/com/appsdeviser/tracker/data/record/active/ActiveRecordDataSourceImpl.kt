package com.appsdeviser.tracker.data.record.active

import app.cash.sqldelight.coroutines.asFlow
import com.appsdeviser.core_db.flows.CommonFlow
import com.appsdeviser.core_db.flows.toCommonFlow
import com.appsdeviser.core_db.sqldelight.TimeTrackerDatabase
import com.appsdeviser.tracker.domain.record.active.ActiveRecordDataSource
import com.appsdeviser.tracker.domain.record.active.ActiveRecordItem
import kotlinx.coroutines.flow.map

class ActiveRecordDataSourceImpl(
    db: TimeTrackerDatabase
): ActiveRecordDataSource {

    private val queries = db.timetrackerQueries

    override fun getActiveRecord(): CommonFlow<ActiveRecordItem?> {
        return queries
            .getActiveRecord()
            .asFlow()
            .map {
                return@map if(it.executeAsList().isEmpty()){
                    null
                } else {
                    it.executeAsList().map { singleActiveRecord ->
                        singleActiveRecord.toActiveRecordItem()
                    }.first()
                }
            }.toCommonFlow()
    }

    override suspend fun insertActiveRecord(item: ActiveRecordItem) {
        queries.insertActiveRecord(
            startDate = item.startDate,
            startTime = item.startTime,
            categoryId = item.categoryId
        )
    }

    override suspend fun clearActiveRecord() {
        queries.clearActiveRecord()
    }
}
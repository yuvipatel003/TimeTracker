package com.appsdeviser.tracker.domain.record.active

import com.appsdeviser.core_db.flows.CommonFlow

interface ActiveRecordDataSource {

    fun getActiveRecord(): CommonFlow<ActiveRecordItem?>

    suspend fun insertActiveRecord(item: ActiveRecordItem)

    suspend fun clearActiveRecord()
}
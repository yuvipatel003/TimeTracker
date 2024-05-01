package com.appsdeviser.tracker.data.record

import com.appsdeviser.core_common.utils.toDateString
import com.appsdeviser.core_common.utils.toTimeString
import com.appsdeviser.tracker.domain.record.RecordItem
import database.RecordEntity

fun RecordEntity.toRecordItem(): RecordItem {
    return RecordItem(
        id = this.id,
        startDate = this.startDate.toDateString(),
        startTime = this.startTime.toTimeString(),
        endTime = this.endTime.toTimeString(),
        endDate = this.endDate.toDateString(),
        totalTime = this.totalTime,
        totalAmount = this.totalAmount,
        categoryType = this.categoryType,
        categoryName = this.categoryName,
        rate = this.rate,
        isPaid = this.isPaid == 1L,
        lastUpdated = this.lastUpdated.toDateString()
    )
}
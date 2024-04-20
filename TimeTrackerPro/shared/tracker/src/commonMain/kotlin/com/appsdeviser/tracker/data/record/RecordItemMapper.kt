package com.appsdeviser.tracker.data.record

import com.appsdeviser.tracker.domain.record.RecordItem
import database.RecordEntity

fun RecordEntity.toRecordItem(): RecordItem {
    return RecordItem(
        id = this.id,
        date = this.date,
        startTime = this.startTime,
        endTime = this.endTime,
        totalTime = this.totalTime,
        totalAmount = this.totalAmount,
        categoryType = this.categoryType,
        categoryName = this.categoryName,
        rate = this.rate,
        isPaid = this.isPaid == 1L,
        lastUpdated = this.lastUpdated
    )
}
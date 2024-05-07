package com.appsdeviser.tracker.data.record.active

import com.appsdeviser.tracker.domain.record.active.ActiveRecordItem
import database.ActiveRecordEntity

fun ActiveRecordEntity.toActiveRecordItem(): ActiveRecordItem {
    return ActiveRecordItem(
        id = this.id,
        startDate = this.startDate,
        startTime = this.startTime,
        categoryId = this.categoryId
    )
}
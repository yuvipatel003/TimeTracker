package com.appsdeviser.tracker.presentation.record.view.mapper

import com.appsdeviser.tracker.domain.category.CategoryItem
import com.appsdeviser.tracker.domain.record.RecordItem
import com.appsdeviser.tracker.presentation.record.view.UIRecordItem

fun RecordItem.toUIRecordItem(categoryItem: CategoryItem?): UIRecordItem?{
    categoryItem?.let {
        return UIRecordItem(
            id = this.id,
            startDate = this.startDate,
            startTime = this.startTime,
            endTime = this.endTime,
            endDate = this.endDate,
            totalTime = this.totalTime,
            totalAmount = this.totalAmount,
            categoryId = this.categoryId,
            categoryType = categoryItem.type,
            categoryName = categoryItem.name,
            categoryRate = categoryItem.rate.toString(),
            categoryColor = categoryItem.color,
            isPaid = this.isPaid,
            note = this.note,
            lastUpdated = this.lastUpdated
        )
    }
    return null
}
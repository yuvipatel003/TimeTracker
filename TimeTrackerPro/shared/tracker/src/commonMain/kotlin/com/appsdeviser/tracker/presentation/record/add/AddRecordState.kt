package com.appsdeviser.tracker.presentation.record.add

import com.appsdeviser.core_common.utils.error.Error
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem
import com.appsdeviser.tracker.domain.category.CategoryItem
import com.appsdeviser.tracker.domain.record.RecordItem

data class AddRecordState(
    val selectedCategory: CategoryItem? = null,
    val selectedRecord: RecordItem = RecordItem(
        id = null,
        startDate = "",
        startTime = "",
        endTime = "",
        endDate = "",
        totalTime = "",
        totalAmount = "",
        categoryId = 0,
        isPaid = false,
        note = "",
        lastUpdated = ""
    ),
    val listOfCategoryItem: List<CategoryItem> = emptyList(),
    val recordPageSettingItem: ShowRecordPageSettingItem? = null,
    val enableAddButton: Boolean = false,
    val event: AddRecordEvent? = null,
    val error: Error? = null
)
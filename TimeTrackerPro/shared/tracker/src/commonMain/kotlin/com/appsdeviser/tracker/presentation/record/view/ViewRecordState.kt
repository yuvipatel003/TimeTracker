package com.appsdeviser.tracker.presentation.record.view

import com.appsdeviser.core_common.utils.error.Error
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem
import com.appsdeviser.tracker.domain.category.CategoryItem

data class ViewRecordState(
    val isLoading: Boolean = false,
    val endReached: Boolean = false,
    val page: Int = 0,
    val event: ViewRecordEvent? = null,
    val error: Error? = null,
    val refreshElement: UIRecordItem? = null,
    val showRecordPageSettingItem: ShowRecordPageSettingItem = ShowRecordPageSettingItem(
        id = null,
        dateFormat = "",
        timeFormatAMPM = false,
        showUnit = true,
        showRate = false,
        showTotalAmount = true,
        showPaidCheck = false,
        showCategory = true,
        showCategoryName = true,
        showOnlyFavouriteOnHome = false

    ),
    val listOfRecords: List<UIRecordItem> = emptyList(),
    val filterRecordState: FilterRecordState? = null,
    val listOfCategoryItem: List<CategoryItem> = emptyList()
)
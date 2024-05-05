package com.appsdeviser.tracker.presentation.record.view

import com.appsdeviser.core_common.utils.error.Error
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem

data class ViewRecordState(
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
    val listOfRecords: List<UIRecordItem> = emptyList()
)
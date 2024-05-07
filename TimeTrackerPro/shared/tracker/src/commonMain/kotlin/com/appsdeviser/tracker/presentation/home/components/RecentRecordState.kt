package com.appsdeviser.tracker.presentation.home.components

import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem
import com.appsdeviser.tracker.domain.record.RecordItem

data class RecentRecordState(
    val listOfRecentRecords: List<RecordItem> = emptyList(),
    val loadShowRecordSetting: ShowRecordPageSettingItem = ShowRecordPageSettingItem(
        id = null,
        dateFormat = "",
        timeFormatAMPM = false,
        showUnit = false,
        showRate = false,
        showTotalAmount = false,
        showPaidCheck = false,
        showCategory = false,
        showCategoryName = false,
        showOnlyFavouriteOnHome = false
    )
)
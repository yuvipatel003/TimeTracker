package com.appsdeviser.core_db.data.showrecordpage

import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem
import database.ShowRecordPagesettingsEntity

fun ShowRecordPagesettingsEntity.toShowRecordPageSettingItem(): ShowRecordPageSettingItem {
    return ShowRecordPageSettingItem(
        id = this.id,
        dateFormat = this.dateFormat,
        timeFormatAMPM = this.timeFormatAMPM == 1L,
        showUnit = this.showUnit == 1L,
        showRate = this.showRate == 1L,
        showTotalAmount = this.showTotalAmount == 1L,
        showPaidCheck = this.showPaidCheck == 1L,
        showCategory = this.showCategory == 1L,
        showCategoryName = this.showCategoryName == 1L,
        showOnlyFavouriteOnHome = this.showOnlyFavouriteOnHome == 1L
    )
}
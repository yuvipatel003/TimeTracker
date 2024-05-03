package com.appsdeviser.core_db.data.showrecordpage

import app.cash.sqldelight.coroutines.asFlow
import com.appsdeviser.core_common.utils.toLong
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem
import com.appsdeviser.core_db.flows.CommonFlow
import com.appsdeviser.core_db.flows.toCommonFlow
import com.appsdeviser.core_db.sqldelight.TimeTrackerDatabase
import kotlinx.coroutines.flow.map

class ShowRecordPageSettingDataSourceImpl(
    db: TimeTrackerDatabase
) : ShowRecordPageSettingDataSource {

    private val queries = db.timetrackerQueries

    override fun getShowRecordSetting(): CommonFlow<ShowRecordPageSettingItem> {
        return queries
            .getShowRecordPageSettings()
            .asFlow()
            .map {
                return@map if (it.executeAsList().isEmpty()) {
                    val item = ShowRecordPageSettingItem(
                        id = 0,
                        dateFormat = "",
                        timeFormatAMPM = true,
                        showUnit = true,
                        showRate = false,
                        showTotalAmount = true,
                        showPaidCheck = false,
                        showCategory = true,
                        showCategoryName = false,
                        showOnlyFavouriteOnHome = false
                    )
                    insertShowRecordSetting(item)
                    item
                } else {
                    it.executeAsList().map { singleShowRecordSetting ->
                        singleShowRecordSetting.toShowRecordPageSettingItem()
                    }.first()
                }
            }.toCommonFlow()
    }

    override suspend fun insertShowRecordSetting(item: ShowRecordPageSettingItem) {
        queries.insertShowRecordPageSettings(
            dateFormat = item.dateFormat,
            timeFormatAMPM = item.timeFormatAMPM.toLong(),
            showUnit = item.showUnit.toLong(),
            showRate = item.showRate.toLong(),
            showTotalAmount = item.showTotalAmount.toLong(),
            showPaidCheck = item.showPaidCheck.toLong(),
            showCategory = item.showCategory.toLong(),
            showCategoryName = item.showCategoryName.toLong(),
            showOnlyFavouriteOnHome = item.showOnlyFavouriteOnHome.toLong()
        )
    }

    override suspend fun clearShowRecordSettings() {
        queries.clearShowRecordPageSettings()
    }
}
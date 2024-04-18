package com.appsdeviser.core_db.domain.showrecordpage

import com.appsdeviser.core_db.flows.CommonFlow

interface ShowRecordPageSettingDataSource {

    fun getShowRecordSetting(): CommonFlow<ShowRecordPageSettingItem>

    suspend fun insertShowRecordSetting(item: ShowRecordPageSettingItem)

    suspend fun clearShowRecordSettings()
}
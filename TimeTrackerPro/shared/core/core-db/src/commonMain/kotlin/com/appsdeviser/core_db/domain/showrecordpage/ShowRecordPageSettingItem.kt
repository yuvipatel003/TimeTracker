package com.appsdeviser.core_db.domain.showrecordpage

@kotlinx.serialization.Serializable
data class ShowRecordPageSettingItem(
    val id: Long?,
    val dateFormat: String,
    val timeFormatAMPM: Boolean,
    val showUnit: Boolean,
    val showRate: Boolean,
    val showTotalAmount: Boolean,
    val showPaidCheck: Boolean,
    val showCategory: Boolean,
    val showCategoryName: Boolean,
    val showOnlyFavouriteOnHome: Boolean
)

package com.appsdeviser.core_common.presentation

import com.appsdeviser.core_common.utils.NONE
import com.appsdeviser.core_common.utils.listOfCategory

actual class UiCategory(
    actual val categoryType: String,
    val imageName: String
) {
    actual companion object {
        actual fun byType(type: String): UiCategory {
            return allCategory.firstOrNull {
                it.categoryType == type
            } ?: byType(NONE)
        }

        actual val allCategory: List<UiCategory>
            get() = listOfCategory.map {
                UiCategory(
                    categoryType = it,
                    imageName = it.lowercase()
                )
            }
    }
}
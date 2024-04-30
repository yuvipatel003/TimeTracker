package com.appsdeviser.core_common.presentation

expect class UiCategory {
    val categoryType: String

    companion object {
        fun byType(type: String): UiCategory
        val allCategory: List<UiCategory>
    }
}
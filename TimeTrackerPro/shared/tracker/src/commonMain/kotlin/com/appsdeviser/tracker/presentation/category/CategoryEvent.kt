package com.appsdeviser.tracker.presentation.category

import com.appsdeviser.tracker.domain.category.CategoryItem

sealed class CategoryEvent {
    data object OnErrorSeen : CategoryEvent()
    data class AddCategory(val categoryItem: CategoryItem) : CategoryEvent()
    data class SelectCategory(val categoryItem: CategoryItem?) : CategoryEvent()
    data class RemoveCategory(val categoryItem: CategoryItem) : CategoryEvent()
    data class AddRecordToCategory(val categoryItem: CategoryItem) : CategoryEvent()
    data class MarkFavourite(val categoryItem: CategoryItem, val isFavourite: Boolean) :
        CategoryEvent()
}
package com.appsdeviser.tracker.presentation.category

import com.appsdeviser.core_common.utils.error.Error
import com.appsdeviser.tracker.domain.category.CategoryItem

data class CategoryState(
    val selectedCategory: CategoryItem? = null,
    val listOfCategoryItem: List<CategoryItem> = emptyList(),
    val event: CategoryEvent? = null,
    val error: Error? = null
)
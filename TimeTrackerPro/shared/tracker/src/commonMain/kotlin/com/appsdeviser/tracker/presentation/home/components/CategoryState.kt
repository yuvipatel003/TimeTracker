package com.appsdeviser.tracker.presentation.home.components

import com.appsdeviser.tracker.domain.category.CategoryItem

data class CategoryState(
    val favouriteCategory: CategoryItem? = null,
    val listOfCategory: List<CategoryItem> = emptyList()
)

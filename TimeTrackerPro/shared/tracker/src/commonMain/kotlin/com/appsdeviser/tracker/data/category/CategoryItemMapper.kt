package com.appsdeviser.tracker.data.category

import com.appsdeviser.tracker.domain.category.CategoryItem
import database.CategoryEntity

fun CategoryEntity.toCategoryItem(): CategoryItem {
    return CategoryItem(
        id = this.id,
        type = this.type,
        name = this.name,
        rate = this.rate.toLong(),
        favourite = this.favourite == 1L
    )
}
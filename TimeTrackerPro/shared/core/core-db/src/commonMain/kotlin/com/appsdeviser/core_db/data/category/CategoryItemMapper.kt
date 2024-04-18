package com.appsdeviser.core_db.data.category

import com.appsdeviser.core_db.domain.category.CategoryItem
import database.CategoryEntity

fun CategoryEntity.toCategoryItem(): CategoryItem {
    return CategoryItem(
        id = this.id,
        type = this.type,
        name = this.name,
        rate = this.rate
    )
}
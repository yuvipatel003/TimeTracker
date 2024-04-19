package com.appsdeviser.tracker.domain.category

import com.appsdeviser.core_db.flows.CommonFlow

interface CategoryDataSource {

    fun getCategoryList(): CommonFlow<List<CategoryItem>>

    fun getCategory(id: Long): CommonFlow<CategoryItem?>

    suspend fun insertCategory(item: CategoryItem)

    suspend fun deleteCategory(item: CategoryItem)

    suspend fun setCategoryToFavourite(item: CategoryItem)

    suspend fun removeCategoryFromFavourite(item: CategoryItem)

    suspend fun clearCategoryList()
}
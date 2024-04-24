package com.appsdeviser.tracker.data.category

import app.cash.sqldelight.coroutines.asFlow
import com.appsdeviser.core_common.utils.toLong
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.category.CategoryItem
import com.appsdeviser.core_db.flows.CommonFlow
import com.appsdeviser.core_db.flows.toCommonFlow
import com.appsdeviser.core_db.sqldelight.TimeTrackerDatabase
import kotlinx.coroutines.flow.map

class CategoryDataSourceImpl(
    db: TimeTrackerDatabase
): CategoryDataSource {

    private val queries = db.timetrackerQueries

    override fun getCategoryList(): CommonFlow<List<CategoryItem>> {
        return queries
            .getCategoryList()
            .asFlow()
            .map {
                it.executeAsList().map { categoryEntity ->
                    categoryEntity.toCategoryItem()
                }
            }.toCommonFlow()
    }

    override fun getCategory(id: Long): CommonFlow<CategoryItem?> {
        return queries
            .getCategory(id)
            .asFlow()
            .map {
                return@map if(it.executeAsList().isEmpty()) {
                    null
                } else {
                    it.executeAsList().map { singleCategory ->
                        singleCategory.toCategoryItem()
                    }.first()
                }
            }.toCommonFlow()
    }

    override suspend fun insertCategory(item: CategoryItem) {
        queries.insertCategory(
            id = item.id,
            type = item.type,
            name = item.name,
            favourite = item.favourite.toLong(),
            rate = item.rate,
            color = item.color
        )
    }

    override suspend fun deleteCategory(item: CategoryItem) {
        queries.deleteCategory(id = item.id ?: -1)
    }

    override suspend fun setCategoryToFavourite(item: CategoryItem) {
        queries.removeAllCategoryFromFavourite()
        queries.addCategoryToFavourite(item.id ?: -1)
    }

    override suspend fun removeCategoryFromFavourite(item: CategoryItem) {
        queries.removeCategoryFromFavourite(item.id ?: -1)
    }

    override suspend fun clearCategoryList() {
        queries.clearCategory()
    }
}
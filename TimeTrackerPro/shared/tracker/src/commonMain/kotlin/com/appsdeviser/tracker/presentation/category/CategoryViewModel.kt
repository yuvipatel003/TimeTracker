package com.appsdeviser.tracker.presentation.category

import com.appsdeviser.core_common.utils.error.UiError
import com.appsdeviser.core_db.flows.toCommonStateFlow
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.category.CategoryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val categoryDataSource: CategoryDataSource,
    coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(CategoryState())

    val state = combine(
        _state,
        categoryDataSource.getCategoryList()
    ) { state, categoryList ->
        _state.update {
            it.copy(
                listOfCategoryItem = categoryList
            )
        }
        state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CategoryState())
        .toCommonStateFlow()

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.AddCategory -> updateCategory(event.categoryItem)
            is CategoryEvent.MarkFavourite -> markCategoryAsFavourite(
                event.categoryItem,
                event.isFavourite
            )

            CategoryEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }

            is CategoryEvent.RemoveCategory -> removeCategory(event.categoryItem)
            is CategoryEvent.SelectCategory -> {
                _state.update {
                    it.copy(
                        selectedCategory = event.categoryItem
                    )
                }
            }
            else -> Unit
        }
    }

    private fun markCategoryAsFavourite(categoryItem: CategoryItem, isFavourite: Boolean) {
        viewModelScope.launch {
            if (isFavourite) {
                categoryDataSource.setCategoryToFavourite(categoryItem)
            } else {
                categoryDataSource.removeCategoryFromFavourite(categoryItem)
            }
        }
        _state.update {
            it.copy(
                error = UiError.Notification.UPDATED
            )
        }
    }

    private fun removeCategory(categoryItem: CategoryItem) {
        viewModelScope.launch {
            categoryDataSource.deleteCategory(categoryItem)
        }
        _state.update {
            it.copy(
                error = UiError.Notification.UPDATED
            )
        }
    }

    private fun updateCategory(categoryItem: CategoryItem) {
        viewModelScope.launch {
            categoryDataSource.insertCategory(categoryItem)
        }
        _state.update {
            it.copy(
                selectedCategory = null,
                error = UiError.Notification.UPDATED
            )
        }
    }
}
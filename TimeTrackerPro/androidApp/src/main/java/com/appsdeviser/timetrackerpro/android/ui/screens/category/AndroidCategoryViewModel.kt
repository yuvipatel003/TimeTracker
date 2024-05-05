package com.appsdeviser.timetrackerpro.android.ui.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.record.RecordDataSource
import com.appsdeviser.tracker.presentation.category.CategoryEvent
import com.appsdeviser.tracker.presentation.category.CategoryViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidCategoryViewModel @Inject constructor(
    private val categoryDataSource: CategoryDataSource,
    private val recordDataSource: RecordDataSource
) : ViewModel() {

    private val viewModel by lazy {
        CategoryViewModel(
            categoryDataSource = categoryDataSource,
            recordDataSource = recordDataSource,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: CategoryEvent) {
        viewModel.onEvent(event)
    }
}
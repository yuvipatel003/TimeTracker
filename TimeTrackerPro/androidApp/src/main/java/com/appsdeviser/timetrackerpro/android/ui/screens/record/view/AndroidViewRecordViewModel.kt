package com.appsdeviser.timetrackerpro.android.ui.screens.record.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.record.RecordDataSource
import com.appsdeviser.tracker.presentation.record.view.ViewRecordEvent
import com.appsdeviser.tracker.presentation.record.view.ViewRecordViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidViewRecordViewModel @Inject constructor(
    private val categoryDataSource: CategoryDataSource,
    private val recordDataSource: RecordDataSource,
    private val recordPageSettingDataSource: ShowRecordPageSettingDataSource
) : ViewModel() {

    private val viewModel by lazy {
        ViewRecordViewModel(
            categoryDataSource = categoryDataSource,
            recordDataSource = recordDataSource,
            recordPageSettingDataSource = recordPageSettingDataSource,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: ViewRecordEvent) {
        viewModel.onEvent(event)
    }
}
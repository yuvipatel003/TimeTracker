package com.appsdeviser.timetrackerpro.android.ui.screens.record.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.record.RecordDataSource
import com.appsdeviser.tracker.presentation.record.add.AddRecordEvent
import com.appsdeviser.tracker.presentation.record.add.AddRecordViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidAddRecordViewModel @Inject constructor(
    private val categoryDataSource: CategoryDataSource,
    private val recordDataSource: RecordDataSource,
    private val recordPageSettingDataSource: ShowRecordPageSettingDataSource
) : ViewModel() {

    private val viewModel by lazy {
        AddRecordViewModel(
            categoryDataSource = categoryDataSource,
            recordDataSource = recordDataSource,
            recordPageSettingDataSource = recordPageSettingDataSource,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: AddRecordEvent) {
        viewModel.onEvent(event)
    }
}
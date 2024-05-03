package com.appsdeviser.tracker.presentation.record.view

import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.core_db.flows.toCommonStateFlow
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.record.RecordDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ViewRecordViewModel(
    private val categoryDataSource: CategoryDataSource,
    private val recordDataSource: RecordDataSource,
    private val recordPageSettingDataSource: ShowRecordPageSettingDataSource,
    coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(ViewRecordState())

    val state = combine(
        _state,
        recordDataSource.getRecordList(0)
    ) { state, recordList ->
        _state.update {
            it.copy(
                listOfRecords = recordList
            )
        }
        state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ViewRecordState())
        .toCommonStateFlow()

    fun onEvent(event: ViewRecordEvent) {
    }
}
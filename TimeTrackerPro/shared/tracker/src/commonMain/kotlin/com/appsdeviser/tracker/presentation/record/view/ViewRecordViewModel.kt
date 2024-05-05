package com.appsdeviser.tracker.presentation.record.view

import com.appsdeviser.core_common.utils.addAllIfNotExist
import com.appsdeviser.core_common.utils.replace
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.core_db.flows.toCommonStateFlow
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.category.CategoryItem
import com.appsdeviser.tracker.domain.record.RecordDataSource
import com.appsdeviser.tracker.domain.record.RecordItem
import com.appsdeviser.tracker.presentation.record.view.mapper.toUIRecordItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewRecordViewModel(
    private val categoryDataSource: CategoryDataSource,
    private val recordDataSource: RecordDataSource,
    private val recordPageSettingDataSource: ShowRecordPageSettingDataSource,
    coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(ViewRecordState())
    private val recordOffSet = MutableStateFlow(0L)
    private val listOfCategory = MutableStateFlow<List<CategoryItem>>(emptyList())
    private val recordsList = mutableListOf<UIRecordItem>()

    val state = combine(
        _state,
        recordPageSettingDataSource.getShowRecordSetting()
    ) { state, recordPageSetting ->
        _state.update {
            it.copy(
                showRecordPageSettingItem = recordPageSetting
            )
        }
        state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ViewRecordState())
        .toCommonStateFlow()

    init {
        loadCategory()
        loadRecords(recordOffSet.value)
    }

    private fun loadCategory() {
        viewModelScope.launch {
            listOfCategory.value = categoryDataSource.getCategoryList().first()
        }
    }

    private fun deleteRecord(uiRecordItem: UIRecordItem) {
        viewModelScope.launch {
            recordDataSource.deleteRecord(
                RecordItem(
                    id = uiRecordItem.id,
                    startDate = uiRecordItem.startDate,
                    startTime = uiRecordItem.startTime,
                    endTime = uiRecordItem.endTime,
                    endDate = uiRecordItem.endDate,
                    totalTime = uiRecordItem.totalTime,
                    totalAmount = uiRecordItem.totalAmount,
                    categoryId = uiRecordItem.categoryId,
                    isPaid = uiRecordItem.isPaid,
                    note = uiRecordItem.note,
                    lastUpdated = uiRecordItem.lastUpdated
                )
            )
            recordsList.remove(uiRecordItem)
            _state.update {
                it.copy(
                    listOfRecords = recordsList,
                    event = null
                )
            }
        }
    }

    private fun loadRecords(offSet: Long) {
        viewModelScope.launch {
            val listOfRecords =
                recordDataSource.getRecordList(offSet).first().mapNotNull { recordItem ->
                    recordItem.toUIRecordItem(listOfCategory.value.firstOrNull { it.id == recordItem.categoryId })
                }
            recordsList.addAllIfNotExist(listOfRecords)
            _state.update {
                it.copy(
                    listOfRecords = recordsList
                )
            }
        }
    }

    private fun refreshSingleElement(uiRecordItem: UIRecordItem) {
        viewModelScope.launch {
            val singleRecord =
                recordDataSource.getRecord(uiRecordItem.id ?: -1).mapNotNull { recordItem ->
                    recordItem?.toUIRecordItem(listOfCategory.value.firstOrNull { it.id == recordItem.categoryId })
                }
            recordsList.replace(uiRecordItem, singleRecord.first())
            _state.update {
                it.copy(
                    listOfRecords = recordsList,
                    event = null,
                    refreshElement = null
                )
            }
        }
    }

    fun onEvent(event: ViewRecordEvent) {
        when (event) {
            ViewRecordEvent.LoadNextRecords -> {
                recordOffSet.value = recordOffSet.value + 1
                loadRecords(recordOffSet.value)
            }

            ViewRecordEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }

            is ViewRecordEvent.UpdateElement -> {
                refreshSingleElement(event.uiRecordItem)
            }

            is ViewRecordEvent.MarkElementToUpdate -> {
                _state.update {
                    it.copy(
                        event = null,
                        refreshElement = event.uiRecordItem
                    )
                }
            }

            is ViewRecordEvent.DeleteRecord -> {
                deleteRecord(event.uiRecordItem)
            }

            else -> Unit
        }
    }
}
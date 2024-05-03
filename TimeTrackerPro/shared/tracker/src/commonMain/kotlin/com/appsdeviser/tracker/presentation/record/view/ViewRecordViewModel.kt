package com.appsdeviser.tracker.presentation.record.view

import com.appsdeviser.core_common.utils.addAllIfNotExist
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.core_db.flows.toCommonStateFlow
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.category.CategoryItem
import com.appsdeviser.tracker.domain.record.RecordDataSource
import com.appsdeviser.tracker.presentation.record.view.mapper.toUIRecordItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
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
    private val recordGroupByList = mutableListOf<Pair<String, List<UIRecordItem>>>()

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
        _state.update {
            it.copy(
                listOfRecords = recordGroupByList
            )
        }
    }

    private fun loadCategory() {
        viewModelScope.launch {
            listOfCategory.value = categoryDataSource.getCategoryList().first()
        }
    }

    private fun loadRecords(offSet: Long) {
        viewModelScope.launch {
            val listOfRecords = recordDataSource.getRecordList(offSet).first().mapNotNull { recordItem ->
                recordItem.toUIRecordItem(listOfCategory.value.firstOrNull { it.id == recordItem.categoryId })
            }

            val groupRecordByDate = listOfRecords.sortedByDescending {
                it.startDate
            }.groupBy {
                it.startDate
            }.toList()
            recordGroupByList.addAllIfNotExist(groupRecordByDate)
        }
    }

    private fun refreshRecord(){
        recordGroupByList.clear()
        viewModelScope.launch {
            for(offSetIndex in 0 .. recordOffSet.value) {
                loadRecords(offSetIndex)
            }
            _state.update {
                it.copy(
                    listOfRecords = recordGroupByList
                )
            }
        }
    }

    fun onEvent(event: ViewRecordEvent) {
        when(event) {
            ViewRecordEvent.LoadNextRecords -> {
                recordOffSet.value = recordOffSet.value + 1
                loadRecords(recordOffSet.value)
                _state.update {
                    it.copy(
                        listOfRecords = recordGroupByList,
                        event = null
                    )
                }
            }
            ViewRecordEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }
            is ViewRecordEvent.RefreshRecords -> {
                refreshRecord()
                _state.update {
                    it.copy(
                        event = null,
                        showRefresh = false
                    )
                }
            }
            is ViewRecordEvent.UpdateRefresh -> {
                _state.update {
                    it.copy(
                        event = null,
                        showRefresh = true
                    )
                }
            }
            else -> Unit
        }
    }


}
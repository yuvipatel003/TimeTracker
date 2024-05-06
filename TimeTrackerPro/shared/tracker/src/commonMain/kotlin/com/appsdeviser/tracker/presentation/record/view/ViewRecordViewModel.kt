package com.appsdeviser.tracker.presentation.record.view

import com.appsdeviser.core_common.presentation.DefaultPaginator
import com.appsdeviser.core_common.utils.Result
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
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewRecordViewModel(
    private val categoryDataSource: CategoryDataSource,
    private val recordDataSource: RecordDataSource,
    private val recordPageSettingDataSource: ShowRecordPageSettingDataSource,
    private val filterRecord: FilterRecord,
    coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(ViewRecordState())
    private val listOfCategory = MutableStateFlow<List<CategoryItem>>(emptyList())
    private var recordsList = mutableListOf<UIRecordItem>()

    val state = combine(
        _state,
        recordPageSettingDataSource.getShowRecordSetting(),
        recordDataSource.getRecordList(0),
        categoryDataSource.getCategoryList()
    ) { state, recordPageSetting, records, categoryList ->

        val existingSingleRecord = recordsList.firstOrNull {
            it.id == state.refreshElement?.id
        }
        val newSingleRecord = records.firstOrNull {
            it.id == state.refreshElement?.id
        }
        if (existingSingleRecord != null && newSingleRecord != null) {
            val record =
                newSingleRecord.toUIRecordItem(listOfCategory.value.firstOrNull { it.id == newSingleRecord.categoryId })
            record?.let {
                recordsList.replace(existingSingleRecord, record)
            }
        }
        recordsList.addAllIfNotExist(records.mapNotNull { recordItem ->
            recordItem.toUIRecordItem(listOfCategory.value.firstOrNull { it.id == recordItem.categoryId })
        })
        filterRecords()
        _state.update {
            it.copy(
                showRecordPageSettingItem = recordPageSetting,
                listOfCategoryItem = categoryList
            )
        }
        state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ViewRecordState())
        .toCommonStateFlow()

    private val paginator = DefaultPaginator(
        initialKey = state.value.page,
        onLoadUpdated = { isLoading ->
            _state.update {
                it.copy(
                    isLoading = isLoading
                )
            }
        },
        onRequest = { nextPage ->
            Result.Success(recordDataSource.getRecordList(nextPage.toLong()).first())
        },
        getNextKey = {
            state.value.page + 1
        },
        onError = { error ->
            _state.update {
                it.copy(
                    error = error
                )
            }
        },
        onSuccess = { items, newKey ->
            recordsList.addAllIfNotExist(items.mapNotNull { recordItem ->
                recordItem.toUIRecordItem(listOfCategory.value.firstOrNull { it.id == recordItem.categoryId })
            })
            filterRecords()
            _state.update {
                it.copy(
                    page = newKey,
                    endReached = items.isEmpty()
                )
            }
        }
    )

    init {
        loadCategory()
        loadNextRecords()
    }

    private fun loadNextRecords() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
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
            recordsList = recordsList.filter {
                it != uiRecordItem
            }.toMutableList()
            _state.update {
                it.copy(
                    event = null
                )
            }
        }
    }

    fun onEvent(event: ViewRecordEvent) {
        when (event) {
            ViewRecordEvent.LoadNextRecords -> {
                loadNextRecords()
            }

            ViewRecordEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
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

            is ViewRecordEvent.FilterRecord -> {
                _state.update {
                    it.copy(
                        filterRecordState = event.filterRecordState,
                        event = null
                    )
                }
                filterRecords()
            }

            else -> Unit
        }
    }

    private fun filterRecords() {
        if (state.value.filterRecordState == null) {
            _state.update {
                it.copy(
                    listOfRecords = recordsList
                )
            }
        } else {
            var filteredList = recordsList
            state.value.filterRecordState?.categoryId?.let { categoryId ->
                filteredList = recordsList.filter {
                    it.categoryId == categoryId
                }.toMutableList()
            }

            state.value.filterRecordState?.isPaid?.let { isPaid ->
                filteredList = filteredList.filter {
                    it.isPaid == isPaid
                }.toMutableList()
            }

            state.value.filterRecordState?.startDate?.let { startDate ->
                state.value.filterRecordState?.endDate?.let { endDate ->
                    filteredList =
                        filterRecord.getRecordsBetweenDates(filteredList, startDate, endDate)
                            .toMutableList()
                }
            }
            _state.update {
                it.copy(
                    listOfRecords = filteredList
                )
            }
        }
    }
}
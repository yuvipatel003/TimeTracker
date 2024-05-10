package com.appsdeviser.tracker.presentation.record.add

import com.appsdeviser.core_common.utils.error.UiError
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.core_db.flows.toCommonStateFlow
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.record.RecordDataSource
import com.appsdeviser.tracker.domain.record.RecordItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddRecordViewModel(
    private val categoryDataSource: CategoryDataSource,
    private val recordDataSource: RecordDataSource,
    private val recordPageSettingDataSource: ShowRecordPageSettingDataSource,
    coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(AddRecordState())

    val state = combine(
        _state,
        categoryDataSource.getCategoryList(),
        recordPageSettingDataSource.getShowRecordSetting()
    ) { state, categoryList, recordPageSettings ->
        state.copy(
            listOfCategoryItem = categoryList,
            enableAddButton = validateRecordDataInput(state.selectedRecord),
            recordPageSettingItem = recordPageSettings
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AddRecordState())
        .toCommonStateFlow()

    private fun validateRecordDataInput(selectedRecord: RecordItem?): Boolean {
        selectedRecord?.let {
            return it.categoryId >= 0 && it.startDate.isNotEmpty() &&
                    it.endDate.isNotEmpty() && it.startTime.isNotEmpty() &&
                    it.endTime.isNotEmpty()
        }
        return false
    }

    fun onEvent(event: AddRecordEvent) {
        when (event) {
            is AddRecordEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null,
                        event = null
                    )
                }
            }

            is AddRecordEvent.AddRecord -> {
                addRecord(event.recordItem, event.lastUpdated)
                _state.update {
                    it.copy(
                        event = null
                    )
                }
            }

            is AddRecordEvent.OnSelectRecord -> {
                selectRecord(event.recordId)
                _state.update {
                    it.copy(
                        event = null
                    )
                }
            }

            is AddRecordEvent.OnSelectCategory -> {
                selectCategory(event.categoryId)
                _state.update {
                    it.copy(
                        event = null
                    )
                }
            }

            is AddRecordEvent.UpdateDate -> {
                if (event.isStartDate) {
                    _state.update {
                        it.copy(
                            selectedRecord = it.selectedRecord.copy(
                                startDate = event.date
                            ),
                            event = null
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            selectedRecord = it.selectedRecord.copy(
                                endDate = event.date
                            ),
                            event = null
                        )
                    }
                }
            }

            is AddRecordEvent.UpdateTime -> {
                if (event.isStartTime) {
                    _state.update {
                        it.copy(
                            selectedRecord = it.selectedRecord.copy(
                                startTime = event.time
                            ),
                            event = null
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            selectedRecord = it.selectedRecord.copy(
                                endTime = event.time
                            ),
                            event = null
                        )
                    }
                }
            }

            is AddRecordEvent.UpdateNote -> {
                _state.update {
                    it.copy(
                        selectedRecord = it.selectedRecord.copy(
                            note = event.note
                        ),
                        event = null
                    )
                }
            }

            is AddRecordEvent.Reset -> {
                _state.update {
                    it.copy(
                        selectedRecord = RecordItem(
                            id = null,
                            startDate = "",
                            startTime = "",
                            endTime = "",
                            endDate = "",
                            totalTime = "",
                            totalAmount = "",
                            categoryId = 0,
                            isPaid = false,
                            note = "",
                            lastUpdated = ""
                        ),
                        event = null
                    )
                }
            }

            is AddRecordEvent.UpdateTotalEarning -> {
                _state.update {
                    it.copy(
                        selectedRecord = it.selectedRecord.copy(
                            totalAmount = event.totalEarning
                        )
                    )
                }
            }

            is AddRecordEvent.UpdateTotalHour -> {
                _state.update {
                    it.copy(
                        selectedRecord = it.selectedRecord.copy(
                            totalTime = event.totalHour
                        )
                    )
                }
            }

            is AddRecordEvent.Paid -> {
                _state.update {
                    it.copy(
                        selectedRecord = it.selectedRecord.copy(
                            isPaid = event.isPaid
                        )
                    )
                }
            }
        }
    }

    private fun addRecord(recordItem: RecordItem, lastUpdated: String) {
        viewModelScope.launch {
            recordDataSource.insertRecord(
                recordItem.copy(
                    lastUpdated = lastUpdated
                )
            )
            _state.update {
                it.copy(
                    selectedCategory = null,
                    selectedRecord = RecordItem(
                        id = null,
                        startDate = "",
                        startTime = "",
                        endTime = "",
                        endDate = "",
                        totalTime = "",
                        totalAmount = "",
                        categoryId = 0,
                        isPaid = false,
                        note = "",
                        lastUpdated = ""
                    ),
                    error = UiError.Notification.RECORD_ADDED
                )
            }
        }
    }

    private fun selectRecord(recordId: Long) {
        viewModelScope.launch {
            val recordItem = recordDataSource.getRecord(recordId).first()
            recordItem?.let { record ->
                val categoryItem = categoryDataSource.getCategory(record.categoryId)
                _state.update {
                    it.copy(
                        selectedRecord = record,
                        selectedCategory = categoryItem.first()
                    )
                }
            }
        }
    }

    private fun selectCategory(categoryId: Long) {
        viewModelScope.launch {
            val categoryItem = categoryDataSource.getCategory(categoryId).first()
            categoryItem?.let {
                _state.update {
                    it.copy(
                        selectedCategory = categoryItem,
                        selectedRecord = it.selectedRecord.copy(
                            categoryId = categoryItem.id ?: -1
                        )
                    )
                }
            }
        }
    }
}
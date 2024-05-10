package com.appsdeviser.tracker.presentation.home

import com.appsdeviser.core_common.constants.FeatureKey
import com.appsdeviser.core_common.utils.addAllIfNotExist
import com.appsdeviser.core_common.utils.error.UiError
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.core_db.featuremanager.FeatureManager
import com.appsdeviser.core_db.flows.toCommonStateFlow
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.category.CategoryItem
import com.appsdeviser.tracker.domain.record.RecordDataSource
import com.appsdeviser.tracker.domain.record.RecordItem
import com.appsdeviser.tracker.domain.record.active.ActiveRecordDataSource
import com.appsdeviser.tracker.domain.record.active.ActiveRecordItem
import com.appsdeviser.tracker.presentation.home.components.ActiveRecordState
import com.appsdeviser.tracker.presentation.home.components.CategoryState
import com.appsdeviser.tracker.presentation.home.components.HomeFeatureState
import com.appsdeviser.tracker.presentation.home.components.NotificationState
import com.appsdeviser.tracker.presentation.home.components.RecentRecordState
import com.appsdeviser.tracker.presentation.record.view.UIRecordItem
import com.appsdeviser.tracker.presentation.record.view.mapper.toUIRecordItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val featureManager: FeatureManager,
    private val settingsDataSource: SettingsDataSource,
    private val categoryDataSource: CategoryDataSource,
    private val showRecordPageSettingDataSource: ShowRecordPageSettingDataSource,
    private val recordDataSource: RecordDataSource,
    private val activeRecordDataSource: ActiveRecordDataSource,
    coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(HomeState())
    private val _homeFeatureState = MutableStateFlow(HomeFeatureState())
    private val _recentRecordState = MutableStateFlow(RecentRecordState())
    private val _categoryState = MutableStateFlow(CategoryState())
    private val _notificationState = MutableStateFlow(NotificationState())
    private val _activeRecordState = MutableStateFlow(ActiveRecordState())
    private var recordsList = mutableListOf<UIRecordItem>()

    /**
     * HomeFeatureState
     */
    private val homeFeatureState = combine(
        _homeFeatureState,
        featureManager.isEnabled(FeatureKey.Show_Add_Category_Page),
        featureManager.isEnabled(FeatureKey.Show_Settings_Page),
        featureManager.isEnabled(FeatureKey.Show_Weekly_Target_Page),
        featureManager.isEnabled(FeatureKey.Show_Notification)
    ) { homeFeatureState, showAddCategory, showSetting, showWeeklyTarget, showNotification ->
        _homeFeatureState.update {
            it.copy(
                isAddCategoryFeatureEnabled = showAddCategory,
                isWeeklyTargetFeatureEnabled = showWeeklyTarget,
                isNotificationFeatureEnabled = showNotification,
                isSettingFeatureEnabled = showSetting
            )
        }
        homeFeatureState
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeFeatureState())

    /**
     * CategoryState and NotificationState
     */
    private val categoryState = combine(
        _categoryState,
        categoryDataSource.getCategoryList(),
        _notificationState
    ) { categoryState, categoryList, notificationState ->
        val favouriteCategory = categoryList.firstOrNull { item -> item.favourite }
        _categoryState.update {
            it.copy(
                favouriteCategory = favouriteCategory,
                listOfCategory = categoryList
            )
        }
        _state.update {
            if(it.selectedCategory == null ) {
                it.copy(
                    selectedCategory = favouriteCategory
                )
            } else it
        }
        _state.update {
            it.copy(
                notificationState = notificationState
            )
        }
        categoryState
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CategoryState())

    private val activeRecordState = combine(
        _activeRecordState,
        activeRecordDataSource.getActiveRecord(),
        categoryDataSource.getCategoryList()
    ) { activeRecordState, currentActiveRecord, categoryList ->
        currentActiveRecord?.let { activeRecord ->
            _activeRecordState.update {
                it.copy(
                    isTrackerInProgress = true,
                    startDate = activeRecord.startDate,
                    startTime = activeRecord.startTime,
                    categoryItem = categoryList.firstOrNull { categoryItem ->
                        categoryItem.id == activeRecord.categoryId
                    }
                )
            }
            _state.update {
                it.copy(
                    activeRecordState = activeRecordState
                )
            }
        }
        activeRecordState
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ActiveRecordState())


    /**
     * RecentRecordState
     */
    private val recentRecordState = combine(
        _recentRecordState,
        showRecordPageSettingDataSource.getShowRecordSetting(),
        recordDataSource.getRecordList(),
        recordDataSource.getRecordList(1),
        activeRecordState
    ) { recentRecordState, showRecordSetting, recentRecords, anotherRecentRecord, activeRecordState ->
            recordsList.clear()
            val allRecords = mutableListOf<UIRecordItem>()
            allRecords.addAllIfNotExist(recentRecords.mapNotNull { recordItem ->
                    recordItem.toUIRecordItem(_state.value.categoryState.listOfCategory.firstOrNull { it.id == recordItem.categoryId })
                })
            allRecords.addAllIfNotExist(anotherRecentRecord.mapNotNull { recordItem ->
                recordItem.toUIRecordItem(_state.value.categoryState.listOfCategory.firstOrNull { it.id == recordItem.categoryId })
            })

            if(showRecordSetting.showOnlyFavouriteOnHome && categoryState.value.favouriteCategory?.id != null) {
                recordsList.addAllIfNotExist(allRecords.filter {
                    it.categoryId == categoryState.value.favouriteCategory?.id
                })
            } else {
                recordsList.addAllIfNotExist(allRecords)
            }
            recentRecordState.copy(
                loadShowRecordSetting = showRecordSetting,
                listOfRecentRecords = recordsList
            )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RecentRecordState())

    /**
     *  State for UI use
     */
    val state = combine(
        _state,
        settingsDataSource.getSettings(),
        categoryState,
        homeFeatureState,
        recentRecordState,
    ) { state, settings, categoryState, homeFeatureState, recentRecordState ->
        state.copy(
                userName = settings.userName,
                recentRecordState = recentRecordState,
                categoryState = categoryState,
                homeFeatureState = homeFeatureState
            )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeState())
        .toCommonStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }

            HomeEvent.OnNotificationClick -> {
                _notificationState.update {
                    it.copy(
                        isNotificationOpen = !it.isNotificationOpen
                    )
                }
            }

            HomeEvent.OnNextNotification -> {
                _notificationState.update {
                    if (it.currentPosition < it.listOfNotification.size - 1) {
                        it.copy(
                            currentPosition = it.currentPosition + 1
                        )
                    } else it
                }
            }

            HomeEvent.OnPreviousNotification -> {
                _notificationState.update {
                    if (it.currentPosition > 0) {
                        it.copy(
                            currentPosition = it.currentPosition - 1
                        )
                    } else it
                }
            }

            is HomeEvent.StartRecord -> {
                startActiveRecord(event.categoryItem, event.startDate, event.startTime)
            }

            is HomeEvent.SelectCategory -> {
               _state.update {
                   it.copy(
                       selectedCategory = event.categoryItem,
                       event = null
                   )
               }
            }

            is HomeEvent.EndRecord -> {
                stopActiveRecord(event.recordItem)
            }

            is HomeEvent.ShowAddCategoryWarning -> {
                _state.update {
                    it.copy(
                        error = UiError.Notification.ADD_CATEGORY_WARNING
                    )
                }
            }
            else -> Unit
        }
    }

    private fun startActiveRecord(categoryItem: CategoryItem, startDate: String, startTime: String) {
        viewModelScope.launch {
            activeRecordDataSource.insertActiveRecord(
                item = ActiveRecordItem(
                    id = null,
                    startDate = startDate,
                    startTime = startTime,
                    categoryId = categoryItem.id ?: -1
                )
            )
            _state.update {
                it.copy(
                    event = null
                )
            }
        }
    }

    private fun stopActiveRecord(recordItem: RecordItem) {
        viewModelScope.launch {
            activeRecordDataSource.clearActiveRecord()
            recordDataSource.insertRecord(recordItem)
            _state.update {
                it.copy(
                    event = null,
                    activeRecordState = ActiveRecordState()
                )
            }
        }
    }
}
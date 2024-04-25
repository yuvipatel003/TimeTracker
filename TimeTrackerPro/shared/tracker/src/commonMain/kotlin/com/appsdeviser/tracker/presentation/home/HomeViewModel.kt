package com.appsdeviser.tracker.presentation.home

import com.appsdeviser.core_common.constants.FeatureKey
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.core_db.featuremanager.FeatureManager
import com.appsdeviser.core_db.flows.toCommonStateFlow
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.record.RecordDataSource
import com.appsdeviser.tracker.presentation.home.components.CategoryState
import com.appsdeviser.tracker.presentation.home.components.HomeFeatureState
import com.appsdeviser.tracker.presentation.home.components.NotificationState
import com.appsdeviser.tracker.presentation.home.components.RecentRecordState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val featureManager: FeatureManager,
    private val settingsDataSource: SettingsDataSource,
    private val categoryDataSource: CategoryDataSource,
    private val showRecordPageSettingDataSource: ShowRecordPageSettingDataSource,
    private val recordDataSource: RecordDataSource,
    coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(HomeState())
    private val _homeFeatureState = MutableStateFlow(HomeFeatureState())
    private val _recentRecordState = MutableStateFlow(RecentRecordState())
    private val _categoryState = MutableStateFlow(CategoryState())
    private val _notificationState = MutableStateFlow(NotificationState())

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
        _categoryState.update {
            it.copy(
                favouriteCategory = categoryList.firstOrNull { item -> item.favourite },
                listOfCategory = categoryList
            )
        }
        _state.update {
            it.copy(
                notificationState = notificationState
            )
        }
        categoryState
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CategoryState())

    /**
     * RecentRecordState
     */
    private val recentRecordState = combine(
        _recentRecordState,
        showRecordPageSettingDataSource.getShowRecordSetting(),
        recordDataSource.getRecordList()
    ) { recentRecordState, showRecordSetting, recentRecords ->
        _recentRecordState.update {
            it.copy(
                loadShowRecordSetting = showRecordSetting,
                listOfRecentRecords = recentRecords
            )
        }
        recentRecordState
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RecentRecordState())

    /**
     *  State for UI use
     */
    val state = combine(
        _state,
        settingsDataSource.getSettings(),
        categoryState,
        homeFeatureState,
        recentRecordState
    ) { state, settings, categoryState, homeFeatureState, recentRecordState ->
        _state.update {
            it.copy(
                userName = settings.userName,
                recentRecordState = recentRecordState,
                categoryState = categoryState,
                homeFeatureState = homeFeatureState
            )
        }
        state
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

            HomeEvent.StartOrStopRecord -> {

            }

            else -> Unit
        }
    }
}
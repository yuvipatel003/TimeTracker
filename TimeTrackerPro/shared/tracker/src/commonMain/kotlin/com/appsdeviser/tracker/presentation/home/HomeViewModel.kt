package com.appsdeviser.tracker.presentation.home

import com.appsdeviser.core_common.constants.FeatureKey
import com.appsdeviser.core_db.domain.settings.SettingsDataSource
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingDataSource
import com.appsdeviser.core_db.featuremanager.FeatureManager
import com.appsdeviser.core_db.flows.toCommonStateFlow
import com.appsdeviser.tracker.domain.category.CategoryDataSource
import com.appsdeviser.tracker.domain.record.RecordDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
    private var loadFeatureJob: Job? = null

    val state = combine(
        _state,
        settingsDataSource.getSettings(),
        categoryDataSource.getCategoryList(),
        showRecordPageSettingDataSource.getShowRecordSetting(),
        recordDataSource.getRecordList()
    ) { state, settings, categoryList, showRecordSetting, recentRecords ->
        _state.update {
            it.copy(
                userName = settings.userName,
                loadShowRecordSetting = showRecordSetting,
                favouriteCategory = categoryList.firstOrNull { item -> item.favourite },
                listOfRecentRecords = recentRecords
            )
        }
        state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeState())
        .toCommonStateFlow()

    init {
        checkAllFeatures()
    }

    // Check All featureManager Item and enable and Disable in state from here
    private fun checkAllFeatures() {
        loadFeatureJob = viewModelScope.launch {
            _state.update {
                it.copy(
                    isAddCategoryFeatureEnabled = featureManager.isEnabled(FeatureKey.Show_Add_Category_Page)
                        .first(),
                    isSettingFeatureEnabled = featureManager.isEnabled(FeatureKey.Show_Settings_Page)
                        .first(),
                    isWeeklyTargetFeatureEnabled = featureManager.isEnabled(FeatureKey.Show_Weekly_Target_Page)
                        .first(),
                    isNotificationFeatureEnabled = featureManager.isEnabled(FeatureKey.Show_Notification)
                        .first()
                )
            }
        }
    }

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
                _state.update {
                    it.copy(
                        notificationState = it.notificationState.copy(
                            isNotificationOpen = !it.notificationState.isNotificationOpen
                        )
                    )
                }
            }

            HomeEvent.OnNextNotification -> {
                _state.update {
                    if (it.notificationState.currentPosition < it.notificationState.listOfNotification.size - 1) {
                        it.copy(
                            notificationState = it.notificationState.copy(
                                currentPosition = it.notificationState.currentPosition + 1
                            )
                        )
                    } else it
                }
            }

            HomeEvent.OnPreviousNotification -> {
                _state.update {
                    if (it.notificationState.currentPosition > 0) {
                        it.copy(
                            notificationState = it.notificationState.copy(
                                currentPosition = it.notificationState.currentPosition - 1
                            )
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
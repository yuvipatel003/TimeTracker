package com.appsdeviser.timetrackerpro.android.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.DEFAULT_DB_DATE_FORMAT
import com.appsdeviser.timetrackerpro.android.ui.core.DEFAULT_DB_TIME_FORMAT
import com.appsdeviser.timetrackerpro.android.ui.core.calculateHours
import com.appsdeviser.timetrackerpro.android.ui.core.components.EmptyListView
import com.appsdeviser.timetrackerpro.android.ui.core.displayAsDate
import com.appsdeviser.timetrackerpro.android.ui.core.displayAsTime
import com.appsdeviser.timetrackerpro.android.ui.core.roundToTwoDecimal
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.PrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.onPrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.ClockInView
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.FloatingActionButtonItem
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.HomeTitleBar
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.NotificationView
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.RecentActivity
import com.appsdeviser.tracker.domain.record.RecordItem
import com.appsdeviser.tracker.presentation.home.HomeEvent
import com.appsdeviser.tracker.presentation.home.HomeState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    val spacing = LocalSpacing.current
    var expanded by remember { mutableStateOf(false) }
    val floatingActionButtonPaddingFromBottom = when {
        state.categoryState.listOfCategory.isEmpty() -> spacing.notificationImageSize
        state.selectedCategory == null -> spacing.spaceExtraLarge
        else -> spacing.settingsDateWidth
    }

    // Floating Button
    Scaffold(
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(
                    bottom = floatingActionButtonPaddingFromBottom
                )
            ) {
                if (expanded) {
                    FloatingActionButtonItem(
                        onClick = {
                            onEvent(HomeEvent.ShowCategory)
                        },
                        imageRes = R.drawable.record,
                        title = stringResource(id = R.string.category)
                    )
                    FloatingActionButtonItem(
                        onClick = {
                            onEvent(HomeEvent.ShowRecords)
                        },
                        imageRes = R.drawable.record_list,
                        title = stringResource(id = R.string.view_records)
                    )
                    FloatingActionButtonItem(
                        onClick = {
                            onEvent(HomeEvent.ShowAddNewRecord)
                        },
                        imageRes = R.drawable.record,
                        title = stringResource(id = R.string.add_record)
                    )
                }

                FloatingActionButton(
                    onClick = {
                        expanded = !expanded
                    },
                    containerColor = PrimaryColor,
                    contentColor = onPrimaryColor,
                    modifier = Modifier.clip(CircleShape)
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.Close else Icons.Default.Dashboard,
                        contentDescription = ""
                    )
                }
            }
        }
    ) { paddingValues ->
        // Main content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
                    .scrollable(
                        state = ScrollableState {
                            0.1f
                        },
                        orientation = Orientation.Vertical
                    ),

                ) {

                // TitleBar
                HomeTitleBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(id = R.string.home_welcome) + state.userName,
                    isNotificationFeatureEnabled = !state.homeFeatureState.isNotificationFeatureEnabled,
                    isNotificationSelected = state.notificationState.isNotificationOpen,
                    onNotificationClick = {
                        onEvent(HomeEvent.OnNotificationClick)
                    },
                    isSettingsFeatureEnabled = !state.homeFeatureState.isSettingFeatureEnabled,
                    onSettingsClick = {
                        onEvent(HomeEvent.ShowSetting)
                    }
                )
                Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
                // Notification
                NotificationView(
                    state.notificationState,
                    onCloseClick = {
                        onEvent(HomeEvent.OnNotificationClick)
                    },
                    onNextSwipe = {
                        onEvent(HomeEvent.OnNextNotification)
                    },
                    onPreviousSwipe = {
                        onEvent(HomeEvent.OnPreviousNotification)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceMedium)
                )
                // Notification Indicator
                if (state.notificationState.isNotificationOpen) {
                    LazyRow(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        items(state.notificationState.listOfNotification.size) { item ->
                            val size = if (item == state.notificationState.currentPosition) {
                                spacing.spaceMedium
                            } else {
                                spacing.spaceSmall
                            }
                            Icon(
                                imageVector = Icons.Default.Brightness1,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(size)
                                    .padding(1.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
                // Weekly and Monthly Goal

                // Recent Records or Activity
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceLarge),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.onBackground)
                            .weight(1f)
                    )
                    Text(
                        stringResource(id = R.string.home_recent_activity),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(horizontal = spacing.spaceMedium)
                            .weight(1f)
                    )
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.onBackground)
                            .weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
                RecentActivity(
                    modifier = Modifier.weight(1f)
                )
            }

            if (state.categoryState.listOfCategory.isNotEmpty()) {
                val trackerSelectedCategory =
                    if (state.activeRecordState.isTrackerInProgress) {
                        state.activeRecordState.categoryItem
                    } else {
                        state.selectedCategory
                    }
                val dateFormat = SimpleDateFormat(DEFAULT_DB_DATE_FORMAT, Locale.getDefault())
                val timeFormat = SimpleDateFormat(DEFAULT_DB_TIME_FORMAT, Locale.getDefault())
                val currentTime = Date()
                ClockInView(
                    selectedCategory = trackerSelectedCategory,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    onSelectCategoryItem = {
                        onEvent(HomeEvent.SelectCategory(it))
                    },
                    listOfCategory = state.categoryState.listOfCategory,
                    activeRecordState = state.activeRecordState,
                    showRecordPageSettingItem = state.recentRecordState.loadShowRecordSetting,
                    totalHours = calculateHours(
                        state.activeRecordState.startDate ?: dateFormat.format(currentTime),
                        state.activeRecordState.startTime ?: timeFormat.format(currentTime),
                        dateFormat.format(currentTime),
                        timeFormat.format(currentTime)
                    ).toString(),
                    onClockIn = {
                        trackerSelectedCategory?.let {
                            onEvent(
                                HomeEvent.StartRecord(
                                    it,
                                    timeFormat.format(currentTime),
                                    dateFormat.format(currentTime)
                                )
                            )
                        }
                    },
                    onClockOut = {
                        val startDate = state.activeRecordState.startDate?.displayAsDate(
                            DEFAULT_DB_DATE_FORMAT
                        ) ?: ""
                        val endDate = dateFormat.format(currentTime)
                        val startTime =
                            state.activeRecordState.startTime?.displayAsTime(false) ?: ""
                        val endTime = timeFormat.format(currentTime)

                        val recordItem = RecordItem(
                            id = null,
                            startDate = startDate,
                            startTime = startTime,
                            endDate = endDate,
                            endTime = endTime,
                            totalTime = calculateHours(
                                startDate,
                                startTime,
                                endDate,
                                endTime
                            ).toString(),
                            totalAmount = calculateHours(
                                startDate,
                                startTime,
                                endDate,
                                endTime
                            ).times(state.selectedCategory?.rate ?: 0.0)
                                .roundToTwoDecimal().toString(),
                            categoryId = trackerSelectedCategory?.id ?: -1,
                            isPaid = false,
                            note = "",
                            lastUpdated = endDate
                        )
                        onEvent(HomeEvent.EndRecord(recordItem = recordItem))
                    }
                )
            } else {
                EmptyListView(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    imageVector = Icons.Default.Warning,
                    imageSize = spacing.titleBarIconSize,
                    title = stringResource(R.string.empty_category_home_screen_message),
                    titleStyle = MaterialTheme.typography.bodyMedium,
                    showContainerBorder = true
                )
            }
        }
    }
}
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material.icons.filled.Close
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
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.PrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.onPrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.FloatingActionButtonItem
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.NotificationView
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.RecentActivity
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.HomeTitleBar
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.ActionButton
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.OutlinedActionButton
import com.appsdeviser.tracker.presentation.home.HomeEvent
import com.appsdeviser.tracker.presentation.home.HomeState

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    val spacing = LocalSpacing.current
    var expanded by remember { mutableStateOf(false) }

    // Floating Button
    Scaffold(
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(top = 0.dp, bottom = 112.dp, start = 0.dp, end = 0.dp)
            ) {
                if (expanded) {
                    FloatingActionButtonItem(
                        onClick = {
                            onEvent(HomeEvent.ShowCategoryList)
                        },
                        imageRes = R.drawable.record,
                        title = stringResource(id = R.string.view_all_category)
                    )
                    FloatingActionButtonItem(
                        onClick = {
                            onEvent(HomeEvent.ShowAddNewCategory)
                        },
                        imageRes = R.drawable.record,
                        title = stringResource(id = R.string.add_category)
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
                        imageVector = if (expanded) Icons.Default.Close else Icons.Default.Add,
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
                    .padding(0.dp, 0.dp, 0.dp, spacing.spaceExtraLarge + spacing.spaceExtraLarge)
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

            // Category Button and Start/Stop Button for Shortcut
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(spacing.spaceMedium),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedActionButton(
                    text = "Change Current Category",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { },

                    )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                ActionButton(
                    text = stringResource(id = R.string.home_clock_in),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { },

                    )
            }
        }
    }
}
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness1
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.NotificationView
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.RecentActivity
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.TitleBar
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.ActionButton
import com.appsdeviser.tracker.presentation.home.HomeEvent
import com.appsdeviser.tracker.presentation.home.HomeState
import com.appsdeviser.timetrackerpro.android.R

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .padding(0.dp, 0.dp, 0.dp, spacing.spaceExtraLarge + spacing.spaceMedium)
                .scrollable(
                    state = ScrollableState {
                        0.1f
                    },
                    orientation = Orientation.Vertical
                ),

            ) {

            TitleBar(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.home_welcome) + state.userName,
                isNotificationFeatureEnabled = !state.isNotificationFeatureEnabled,
                isNotificationSelected = state.notificationState.isNotificationOpen,
                onNotificationClick = {
                    onEvent(HomeEvent.OnNotificationClick)
                },
                isSettingsFeatureEnabled = !state.isSettingFeatureEnabled,
                onSettingsClick = {

                }
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
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

        ActionButton(
            text = stringResource(id = R.string.home_clock_in),
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(spacing.spaceMedium)
        )
    }
}
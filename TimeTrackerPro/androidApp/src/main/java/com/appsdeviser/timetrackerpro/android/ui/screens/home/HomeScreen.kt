package com.appsdeviser.timetrackerpro.android.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.TitleBar
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.ActionButton

@Composable
fun HomeScreen() {
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
                .scrollable(state = ScrollableState {
                    0.1f
                },
                    orientation = Orientation.Vertical),

        ) {

            TitleBar(
                modifier = Modifier.fillMaxWidth(),
                title = "Hi, Yuvi",
                onNotificationClick = {
                },
                onSettingsClick = {

                }
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                // Get recent activity here
               items(55) {pair ->
                   Text(
                       text = "This is Home Page" + pair.toString()
                   )
               }
            }
        }

        ActionButton(
            text = "Clock In",
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(spacing.spaceMedium)
            )
    }
}
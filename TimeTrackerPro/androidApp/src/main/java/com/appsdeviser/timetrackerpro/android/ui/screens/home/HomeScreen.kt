package com.appsdeviser.timetrackerpro.android.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.screens.home.components.TitleBar

@Composable
fun HomeScreen() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        val spacing = LocalSpacing.current

        TitleBar(
            modifier = Modifier.fillMaxWidth(),
            title = "Hi, Yuvi",
            onNotificationClick = {

            },
            onSettingsClick = {

            },
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        Text(
            text = "This is Home Page"
        )
    }
}
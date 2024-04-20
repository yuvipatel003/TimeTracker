package com.appsdeviser.timetrackerpro.android.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightBlue
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing

@Composable
fun TitleBar(
    modifier: Modifier = Modifier,
    title: String = "",
    isSettingsFeatureEnabled: Boolean = false,
    onSettingsClick: () -> Unit,
    onNotificationClick: () -> Unit,
    isNotificationSelected: Boolean = false,
    isNotificationFeatureEnabled: Boolean = false
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(spacing.spaceMedium, spacing.spaceSmall),
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Row(
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            if(isNotificationFeatureEnabled) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    modifier = Modifier
                        .size(spacing.titleBarIconSize)
                        .clickable {
                            onNotificationClick()
                        },
                    tint = if (isNotificationSelected) LightBlue else MaterialTheme.colorScheme.onPrimary
                )
            }
            if(isSettingsFeatureEnabled) {
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .size(spacing.titleBarIconSize)
                        .clickable {
                            onSettingsClick()
                        },
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
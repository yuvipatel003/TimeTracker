package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.PrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.onPrimaryColor

@Composable
fun EmptyRecordView(
    modifier: Modifier = Modifier,
    title: String = "",
    message: String? = null
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.record_list),
            modifier = Modifier.size(spacing.settingsDateWidth),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (message == null) {
                Text(
                    stringResource(R.string.tap),
                    modifier = Modifier.padding(end = spacing.spaceSmall),
                    style = MaterialTheme.typography.bodyMedium
                )
                Box(
                    modifier = Modifier
                        .size(spacing.notificationImageSize)
                        .clip(CircleShape)
                        .background(PrimaryColor)
                ) {
                    Icon(
                        imageVector = Icons.Default.Dashboard,
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.Center),
                        tint = onPrimaryColor
                    )
                }
                Text(
                    text = stringResource(R.string.to_add_one),
                    modifier = Modifier.padding(start = spacing.spaceSmall),
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                Text(
                    text = message,
                    modifier = Modifier.padding(start = spacing.spaceSmall),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }
}

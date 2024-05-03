package com.appsdeviser.timetrackerpro.android.ui.screens.home.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack
import com.appsdeviser.tracker.domain.notification.NotificationItem

@Composable
fun NotificationContent(
    notificationItem: NotificationItem,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (notificationItem.image != null) {
            AsyncImage(
                model = Uri.parse(notificationItem.image),
                contentDescription = null,
                modifier = Modifier
                    .size(spacing.notificationImageSize)
                    .padding(spacing.spaceExtraSmall)
            )
        }
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = notificationItem.title,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceSmall),
                style = MaterialTheme.typography.headlineSmall,
                color = TextColorBlack
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = notificationItem.message,
                minLines = 3,
                maxLines = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceSmall),
                style = MaterialTheme.typography.bodyMedium,
                color = TextColorBlack
            )
        }
    }
}
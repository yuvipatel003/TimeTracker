package com.appsdeviser.timetrackerpro.android.ui.screens.whatsnew.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing

@Composable
fun WhatsNewContent(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    description: String
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier
            .padding(spacing.spaceMedium, spacing.spaceExtraLarge),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = Uri.parse(imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .padding(spacing.spaceMedium)
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        Text(
            text = description,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
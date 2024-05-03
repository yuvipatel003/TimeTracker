package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing

@Composable
fun TitleBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(spacing.spaceMedium, spacing.spaceSmallMedium)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = stringResource(id = R.string.navigation_back),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable {
                    onBackClick()
                },
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
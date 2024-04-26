package com.appsdeviser.timetrackerpro.android.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.components.TitleBar
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.screens.settings.components.SettingsText
import com.appsdeviser.tracker.presentation.settings.SettingsEvent
import com.appsdeviser.tracker.presentation.settings.SettingsState

@Composable
fun SettingsScreen(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
    onBackClick: () -> Unit
) {
    val spacing = LocalSpacing.current

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        TitleBar(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.home_settings),
            onBackClick = onBackClick
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmallMedium))
        SettingsText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceExtraSmall),
            value = state.settingsItem?.userName ?: "",
            imageVector = Icons.Default.AccountCircle,
            type = stringResource(id = R.string.username),
            onUpdated = {
                onEvent(SettingsEvent.OnUpdateUsername(it))
            }
        )
        Spacer(modifier = Modifier.height(spacing.spaceSmallMedium))
        SettingsText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceExtraSmall),
            value = state.settingsItem?.email ?: "",
            imageVector = Icons.Default.Email,
            type = stringResource(id = R.string.email),
            onUpdated = {
                onEvent(SettingsEvent.OnUpdateEmail(it))
            }
        )
    }
}
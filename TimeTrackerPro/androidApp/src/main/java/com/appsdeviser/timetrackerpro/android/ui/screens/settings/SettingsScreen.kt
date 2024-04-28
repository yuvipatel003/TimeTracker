package com.appsdeviser.timetrackerpro.android.ui.screens.settings

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.components.TitleBar
import com.appsdeviser.timetrackerpro.android.ui.core.listOfDateFormat
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightPrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.screens.settings.components.DateFormatSelection
import com.appsdeviser.timetrackerpro.android.ui.screens.settings.components.RecordSettingItem
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
        Spacer(modifier = Modifier.height(spacing.spaceSmallMedium))

        state.showRecordPageSettingItem?.let { showRecordSettingsItem ->

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceExtraSmall)
                    .border(
                        width = 0.2.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(spacing.spaceMedium)
                    )
                    .padding(spacing.spaceMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.record_setting),
                    color = LightPrimaryColor,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmallMedium))
                LazyColumn {
                    item {
                        DateFormatSelection(
                            title = stringResource(R.string.date),
                            listOfDateFormat = listOfDateFormat,
                            currentSelection = state.showRecordPageSettingItem?.dateFormat ?: "",
                            onChange = {
                                onEvent(
                                    SettingsEvent.OnUpdateRecordSetting(
                                        showRecordSettingsItem.copy(
                                            dateFormat = it
                                        )
                                    )
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = spacing.spaceSmallMedium)
                        )
                        RecordSettingItem(
                            title = stringResource(R.string.clock_12_hours),
                            isEnabled = showRecordSettingsItem.timeFormatAMPM,
                            modifier = Modifier.fillMaxWidth(),
                            onChange = {
                                onEvent(
                                    SettingsEvent.OnUpdateRecordSetting(
                                        showRecordSettingsItem.copy(
                                            timeFormatAMPM = it
                                        )
                                    )
                                )
                            }
                        )
                        RecordSettingItem(
                            title = stringResource(R.string.total_hours),
                            isEnabled = showRecordSettingsItem.showUnit,
                            modifier = Modifier.fillMaxWidth(),
                            onChange = {
                                onEvent(
                                    SettingsEvent.OnUpdateRecordSetting(
                                        showRecordSettingsItem.copy(
                                            showUnit = it
                                        )
                                    )
                                )
                            }
                        )
                        RecordSettingItem(
                            title = stringResource(R.string.hourly_rate),
                            isEnabled = showRecordSettingsItem.showRate,
                            modifier = Modifier.fillMaxWidth(),
                            onChange = {
                                onEvent(
                                    SettingsEvent.OnUpdateRecordSetting(
                                        showRecordSettingsItem.copy(
                                            showRate = it
                                        )
                                    )
                                )
                            }
                        )
                        RecordSettingItem(
                            title = stringResource(R.string.total_amount),
                            isEnabled = showRecordSettingsItem.showTotalAmount,
                            modifier = Modifier.fillMaxWidth(),
                            onChange = {
                                onEvent(
                                    SettingsEvent.OnUpdateRecordSetting(
                                        showRecordSettingsItem.copy(
                                            showTotalAmount = it
                                        )
                                    )
                                )
                            }
                        )
                        RecordSettingItem(
                            title = stringResource(R.string.paid),
                            isEnabled = showRecordSettingsItem.showPaidCheck,
                            modifier = Modifier.fillMaxWidth(),
                            onChange = {
                                onEvent(
                                    SettingsEvent.OnUpdateRecordSetting(
                                        showRecordSettingsItem.copy(
                                            showPaidCheck = it
                                        )
                                    )
                                )
                            }
                        )
                        RecordSettingItem(
                            title = stringResource(R.string.category_type),
                            isEnabled = showRecordSettingsItem.showCategory,
                            modifier = Modifier.fillMaxWidth(),
                            onChange = {
                                onEvent(
                                    SettingsEvent.OnUpdateRecordSetting(
                                        showRecordSettingsItem.copy(
                                            showCategory = it
                                        )
                                    )
                                )
                            }
                        )
                        RecordSettingItem(
                            title = stringResource(R.string.category_name),
                            isEnabled = showRecordSettingsItem.showCategoryName,
                            modifier = Modifier.fillMaxWidth(),
                            onChange = {
                                onEvent(
                                    SettingsEvent.OnUpdateRecordSetting(
                                        showRecordSettingsItem.copy(
                                            showCategoryName = it
                                        )
                                    )
                                )
                            }
                        )
                        RecordSettingItem(
                            title = stringResource(R.string.favourite_category_only),
                            isEnabled = showRecordSettingsItem.showOnlyFavouriteOnHome,
                            modifier = Modifier.fillMaxWidth(),
                            onChange = {
                                onEvent(
                                    SettingsEvent.OnUpdateRecordSetting(
                                        showRecordSettingsItem.copy(
                                            showOnlyFavouriteOnHome = it
                                        )
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
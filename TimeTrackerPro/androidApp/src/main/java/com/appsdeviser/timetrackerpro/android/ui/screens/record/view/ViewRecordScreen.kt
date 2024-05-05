package com.appsdeviser.timetrackerpro.android.ui.screens.record.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.components.RecordsGroupByDate
import com.appsdeviser.timetrackerpro.android.ui.core.components.SingleFloatingActionButton
import com.appsdeviser.timetrackerpro.android.ui.core.components.TitleBar
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.tracker.presentation.record.view.ViewRecordEvent
import com.appsdeviser.tracker.presentation.record.view.ViewRecordState

@Composable
fun ViewRecordScreen(
    state: ViewRecordState,
    onEvent: (ViewRecordEvent) -> Unit,
    onBackClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                    SingleFloatingActionButton(
                        onClick = {
                            onEvent(ViewRecordEvent.AddRecord)
                        },
                        imgVector = Icons.Default.Add,
                        title = stringResource(R.string.add_new_record)
                    )
            }
        }
    ) { paddingValue ->

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .scrollable(
                    state = ScrollableState {
                        0.1f
                    },
                    orientation = Orientation.Vertical
                )
                .padding(paddingValue)
        ) {
            TitleBar(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.view_records),
                onBackClick = onBackClick
            )

            state.refreshElement?.let {
                Icon(
                    modifier = Modifier
                        .padding(spacing.spaceSmall)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(spacing.spaceSmall)
                        .clickable {
                            onEvent(ViewRecordEvent.UpdateElement(it))
                        }
                        .size(spacing.smallLanguageIcon),
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            val groupRecordByDate = state.listOfRecords.sortedByDescending {
                it.startDate
            }.groupBy {
                it.startDate
            }.toList()

            RecordsGroupByDate(
                modifier = Modifier,
                onEdit = {
                    onEvent(ViewRecordEvent.SelectRecord(it.id ?: -1))
                    onEvent(ViewRecordEvent.MarkElementToUpdate(it))
                },
                onRemove = {
                    onEvent(ViewRecordEvent.DeleteRecord(it))
                },
                listOfGroupByRecords = groupRecordByDate,
                showRecordPageSettingItem = state.showRecordPageSettingItem
            )
        }
    }
}
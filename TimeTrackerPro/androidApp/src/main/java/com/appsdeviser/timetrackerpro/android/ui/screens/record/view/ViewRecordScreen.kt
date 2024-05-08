package com.appsdeviser.timetrackerpro.android.ui.screens.record.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.components.EmptyRecordView
import com.appsdeviser.timetrackerpro.android.ui.core.components.RecordsGroupByDate
import com.appsdeviser.timetrackerpro.android.ui.core.components.SingleFloatingActionButton
import com.appsdeviser.timetrackerpro.android.ui.core.components.TitleBar
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.screens.record.view.components.FilterBottomSheetContent
import com.appsdeviser.tracker.presentation.record.view.ViewRecordEvent
import com.appsdeviser.tracker.presentation.record.view.ViewRecordState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewRecordScreen(
    state: ViewRecordState,
    onEvent: (ViewRecordEvent) -> Unit,
    onBackClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    var isSheetOpen by remember { mutableStateOf(false) }
    var bottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

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

        Box {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
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

                val groupRecordByDate = state.listOfRecords.sortedByDescending {
                    it.startDate
                }.groupBy {
                    it.startDate
                }.toList()

                if(groupRecordByDate.isEmpty()) {
                    EmptyRecordView(
                        modifier = Modifier.fillMaxSize(),
                        title = stringResource(R.string.empty_record_title),
                        message = stringResource(R.string.empty_record_message)
                    )
                } else {
                    RecordsGroupByDate(
                        modifier = Modifier,
                        onEdit = {
                            onEvent(ViewRecordEvent.SelectRecord(it.id ?: -1))
                            onEvent(ViewRecordEvent.MarkElementToUpdate(it))
                        },
                        onRemove = {
                            onEvent(ViewRecordEvent.DeleteRecord(it))
                        },
                        state = state,
                        listOfGroupByRecords = groupRecordByDate,
                        onLoadNextRecords = {
                            onEvent(ViewRecordEvent.LoadNextRecords)
                        }
                    )
                }
            }

            if (!isSheetOpen) {
                Box(modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        bottom = spacing.spaceMedium + spacing.spaceExtraSmall,
                        end = spacing.spaceMedium
                    )
                    .size(spacing.notificationImageSize)
                    .clickable {
                        isSheetOpen = true
                    }
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.FilterAlt,
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(spacing.smallLanguageIcon),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            if (isSheetOpen) {
                ModalBottomSheet(
                    sheetState = bottomSheetState,
                    onDismissRequest = {
                        isSheetOpen = !isSheetOpen
                    }
                ) {
                    FilterBottomSheetContent(
                        onCancelClick = {
                            isSheetOpen = false
                            onEvent(ViewRecordEvent.FilterRecord(null))
                        },
                        onApplyClick = {
                            isSheetOpen = false
                            onEvent(ViewRecordEvent.FilterRecord(it))
                        },
                        filterRecordState = state.filterRecordState,
                        listOfCategoryItem = state.listOfCategoryItem,
                        recordPageSettingItem = state.showRecordPageSettingItem
                    )
                }
            }
        }
    }
}
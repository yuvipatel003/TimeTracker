package com.appsdeviser.timetrackerpro.android.ui.screens.record.add

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.DEFAULT_DB_DATE_FORMAT
import com.appsdeviser.timetrackerpro.android.ui.core.calculateHours
import com.appsdeviser.timetrackerpro.android.ui.core.components.CustomDatePicker
import com.appsdeviser.timetrackerpro.android.ui.core.components.CustomTimePicker
import com.appsdeviser.timetrackerpro.android.ui.core.components.TitleBar
import com.appsdeviser.timetrackerpro.android.ui.core.displayAsDate
import com.appsdeviser.timetrackerpro.android.ui.core.displayAsTime
import com.appsdeviser.timetrackerpro.android.ui.core.formatDate
import com.appsdeviser.timetrackerpro.android.ui.core.roundToTwoDecimal
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.ActionButton
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.OutlinedActionButton
import com.appsdeviser.timetrackerpro.android.ui.screens.record.add.components.AddRecordSelectCategory
import com.appsdeviser.timetrackerpro.android.ui.screens.settings.components.RecordSettingItem
import com.appsdeviser.tracker.presentation.record.add.AddRecordEvent
import com.appsdeviser.tracker.presentation.record.add.AddRecordState
import java.util.Date

@Composable
fun AddRecordScreen(
    state: AddRecordState,
    onEvent: (AddRecordEvent) -> Unit,
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
            title = stringResource(id = R.string.add_record),
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceSmall, vertical = spacing.spaceExtraSmall)
                .border(
                    0.5.dp,
                    MaterialTheme.colorScheme.onBackground,
                    RoundedCornerShape(spacing.spaceMedium)
                )
        ) {
            item {
                AddRecordSelectCategory(
                    selectedCategory = state.selectedCategory,
                    modifier = Modifier,
                    onSelectCategoryItem = {
                        onEvent(AddRecordEvent.OnSelectCategory(it.id ?: -1))
                    },
                    listOfCategory = state.listOfCategoryItem
                )

                Spacer(
                    modifier = Modifier
                        .height(0.2.dp)
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceMediumLarge)
                        .background(MaterialTheme.colorScheme.onBackground)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = spacing.spaceMedium,
                            vertical = spacing.spaceExtraSmall
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.start_date),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = state.selectedRecord.startDate.displayAsDate(
                                state.recordPageSettingItem?.dateFormat ?: DEFAULT_DB_DATE_FORMAT
                            ),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        CustomDatePicker(
                            onSelect = {
                                onEvent(AddRecordEvent.UpdateDate(it, true))
                                onEvent(
                                    AddRecordEvent.UpdateTotalHour(
                                        calculateHours(
                                            it,
                                            state.selectedRecord.startTime,
                                            state.selectedRecord.endDate,
                                            state.selectedRecord.endTime
                                        ).toString()
                                    )
                                )
                                onEvent(
                                    AddRecordEvent.UpdateTotalEarning(
                                        calculateHours(
                                            it,
                                            state.selectedRecord.startTime,
                                            state.selectedRecord.endDate,
                                            state.selectedRecord.endTime
                                        ).times(state.selectedCategory?.rate ?: 0.0)
                                            .roundToTwoDecimal().toString()
                                    )
                                )
                            }
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = spacing.spaceMedium,
                            vertical = spacing.spaceExtraSmall
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.start_time),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = state.selectedRecord.startTime.displayAsTime(
                                state.recordPageSettingItem?.timeFormatAMPM ?: false
                            ),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        CustomTimePicker(
                            onSelect = {
                                onEvent(AddRecordEvent.UpdateTime(it, true))
                                onEvent(
                                    AddRecordEvent.UpdateTotalHour(
                                        calculateHours(
                                            state.selectedRecord.startDate,
                                            it,
                                            state.selectedRecord.endDate,
                                            state.selectedRecord.endTime
                                        ).toString()
                                    )
                                )
                                onEvent(
                                    AddRecordEvent.UpdateTotalEarning(
                                        calculateHours(
                                            state.selectedRecord.startDate,
                                            it,
                                            state.selectedRecord.endDate,
                                            state.selectedRecord.endTime
                                        ).times(state.selectedCategory?.rate ?: 0.0)
                                            .roundToTwoDecimal().toString()
                                    )
                                )
                            }
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = spacing.spaceMedium,
                            vertical = spacing.spaceExtraSmall
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.end_date),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = state.selectedRecord.endDate.displayAsDate(
                                state.recordPageSettingItem?.dateFormat ?: DEFAULT_DB_DATE_FORMAT
                            ),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        CustomDatePicker(
                            onSelect = {
                                onEvent(AddRecordEvent.UpdateDate(it, false))
                                onEvent(
                                    AddRecordEvent.UpdateTotalHour(
                                        calculateHours(
                                            state.selectedRecord.startDate,
                                            state.selectedRecord.startTime,
                                            it,
                                            state.selectedRecord.endTime
                                        ).toString()
                                    )
                                )
                                onEvent(
                                    AddRecordEvent.UpdateTotalEarning(
                                        calculateHours(
                                            state.selectedRecord.startDate,
                                            state.selectedRecord.startTime,
                                            it,
                                            state.selectedRecord.endTime
                                        ).times(state.selectedCategory?.rate ?: 0.0)
                                            .roundToTwoDecimal().toString()
                                    )
                                )
                            }
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = spacing.spaceMedium,
                            vertical = spacing.spaceExtraSmall
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.end_time),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = state.selectedRecord.endTime.displayAsTime(
                                state.recordPageSettingItem?.timeFormatAMPM ?: false
                            ),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        CustomTimePicker(
                            onSelect = {
                                onEvent(AddRecordEvent.UpdateTime(it, false))
                                onEvent(
                                    AddRecordEvent.UpdateTotalHour(
                                        calculateHours(
                                            state.selectedRecord.startDate,
                                            state.selectedRecord.startTime,
                                            state.selectedRecord.endDate,
                                            it
                                        ).toString()
                                    )
                                )
                                onEvent(
                                    AddRecordEvent.UpdateTotalEarning(
                                        calculateHours(
                                            state.selectedRecord.startDate,
                                            state.selectedRecord.startTime,
                                            state.selectedRecord.endDate,
                                            it
                                        ).times(state.selectedCategory?.rate ?: 0.0)
                                            .roundToTwoDecimal().toString()
                                    )
                                )
                            }
                        )
                    }

                }

                Spacer(
                    modifier = Modifier
                        .height(0.2.dp)
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceMediumLarge)
                        .background(MaterialTheme.colorScheme.onBackground)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = spacing.spaceMedium,
                            end = spacing.spaceMedium,
                            top = spacing.spaceMedium,
                            bottom = spacing.spaceSmall
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.total_hours),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = state.selectedRecord.totalTime,
                        modifier = Modifier.padding(end = spacing.spaceSmall)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceMedium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.total_amount),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = state.selectedRecord.totalAmount,
                        modifier = Modifier.padding(end = spacing.spaceSmall)
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(0.2.dp)
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceMediumLarge)
                        .background(MaterialTheme.colorScheme.onBackground)
                )
                RecordSettingItem(
                    title = stringResource(id = R.string.paid),
                    isEnabled = state.selectedRecord.isPaid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall),
                    onChange = {
                        onEvent(AddRecordEvent.Paid(it))
                    },
                    textStyle = MaterialTheme.typography.titleSmall
                )

                TextField(
                    value = state.selectedRecord.note,
                    onValueChange = {
                        onEvent(AddRecordEvent.UpdateNote(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall)
                        .clip(RoundedCornerShape(spacing.spaceSmall)),
                    label = {
                        Text(
                            text = stringResource(R.string.note),
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(bottom = spacing.spaceSmall)
                        )
                    },
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = TextColorBlack
                    ),
                    minLines = 3
                )

                Spacer(
                    modifier = Modifier
                        .padding(horizontal = spacing.spaceMediumLarge,
                            vertical = spacing.spaceSmall)
                        .height(0.2.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onBackground)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceMedium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedActionButton(
                        text = stringResource(id = R.string.reset),
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onEvent(AddRecordEvent.Reset)
                        }
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceMedium))
                    ActionButton(
                        text = stringResource(id = R.string.add),
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onEvent(
                                AddRecordEvent.AddRecord(
                                    state.selectedRecord,
                                    formatDate(Date(), DEFAULT_DB_DATE_FORMAT)
                                )
                            )
                        },
                        isEnabled = state.enableAddButton
                    )
                }
            }
        }
    }
}
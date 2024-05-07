package com.appsdeviser.timetrackerpro.android.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.appsdeviser.core_common.presentation.UiCategory
import com.appsdeviser.core_common.utils.NONE
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.CategoryWithColor
import com.appsdeviser.timetrackerpro.android.ui.core.components.IconWithText
import com.appsdeviser.timetrackerpro.android.ui.core.displayAsTime
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack
import com.appsdeviser.timetrackerpro.android.ui.screens.record.add.components.CategorySelectionPopupForAddRecord
import com.appsdeviser.tracker.domain.category.CategoryItem
import com.appsdeviser.tracker.presentation.home.components.ActiveRecordState

@Composable
fun ClockInView(
    modifier: Modifier = Modifier,
    selectedCategory: CategoryItem? = null,
    listOfCategory: List<CategoryItem> = emptyList(),
    onSelectCategoryItem: (categoryItem: CategoryItem) -> Unit,
    onClockIn: () -> Unit,
    onClockOut: () -> Unit,
    activeRecordState: ActiveRecordState,
    showRecordPageSettingItem: ShowRecordPageSettingItem,
    totalHours: String = ""
) {

    var expanded by remember { mutableStateOf(false) }
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(spacing.spaceExtraSmall)
            .border(
                1.dp,
                MaterialTheme.colorScheme.onBackground,
                RoundedCornerShape(spacing.spaceSmall)
            )
            .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.select),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .weight(3f)
            )
            Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
            CategorySelectionPopupForAddRecord(
                items = listOfCategory,
                modifier = Modifier
                    .weight(1f)
                    .padding(spacing.spaceSmall),
                selectedUiCategory = UiCategory.byType(selectedCategory?.type ?: NONE),
                onSelect = { newCategory ->
                    onSelectCategoryItem(newCategory)
                    expanded = false
                },
                expanded = expanded,
                onExpand = {
                    if (!activeRecordState.isTrackerInProgress) {
                        expanded = true
                    }
                },
                onDismiss = {
                    expanded = false
                },
            )
        }

        selectedCategory?.let { categoryItem ->
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Box(
                modifier = modifier
                    .height(spacing.categoryItemHeight)
                    .clip(RoundedCornerShape(spacing.spaceExtraSmall))
                    .background(
                        CategoryWithColor
                            .fromString(categoryItem.color)
                            .getColor()
                    )
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(horizontal = spacing.spaceSmall),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1.8f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.organization_activity),
                            style = MaterialTheme.typography.bodySmall,
                            color = TextColorBlack
                        )
                        Spacer(
                            modifier = Modifier
                                .height(spacing.spaceSmall)
                        )
                        Text(
                            text = categoryItem.name,
                            style = MaterialTheme.typography.titleSmall,
                            color = TextColorBlack,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    if (activeRecordState.isTrackerInProgress) {
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            IconWithText(
                                drawableRes = R.drawable.start_time,
                                title = activeRecordState.startTime?.displayAsTime(
                                    showRecordPageSettingItem.timeFormatAMPM
                                ) ?: ""
                            )
                            Spacer(modifier = Modifier.height(spacing.spaceSmall))
                            Text(
                                text = totalHours,
                                style = MaterialTheme.typography.titleSmall,
                                color = TextColorBlack,
                            )
                        }

                        Text(
                            text = stringResource(R.string.clock_out),
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = spacing.spaceExtraSmall)
                                .border(
                                    0.5.dp,
                                    TextColorBlack,
                                    RoundedCornerShape(spacing.spaceMedium)
                                )
                                .clickable {
                                    onClockOut()
                                }
                                .padding(
                                    spacing.spaceSmall
                                ),
                            style = MaterialTheme.typography.titleSmall,
                            textAlign = TextAlign.Center,
                            color = TextColorBlack
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.clock_in),
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = spacing.spaceExtraSmall)
                                .border(
                                    0.5.dp,
                                    TextColorBlack,
                                    RoundedCornerShape(spacing.spaceMedium)
                                )
                                .clickable {
                                    onClockIn()
                                }
                                .padding(
                                    spacing.spaceSmall
                                ),
                            style = MaterialTheme.typography.titleSmall,
                            textAlign = TextAlign.Center,
                            color = TextColorBlack
                        )
                    }
                }
            }
        }
    }
}
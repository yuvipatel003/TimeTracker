package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.appsdeviser.core_common.presentation.UiCategory
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.CategoryWithColor
import com.appsdeviser.timetrackerpro.android.ui.core.displayAsTime
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack
import com.appsdeviser.timetrackerpro.android.ui.screens.category.components.CategoryDisplay
import com.appsdeviser.tracker.presentation.record.view.UIRecordItem

@Composable
fun SingleRecordView(
    modifier: Modifier = Modifier,
    recordItem: UIRecordItem,
    showRecordPageSettingItem: ShowRecordPageSettingItem
) {
    val spacing = LocalSpacing.current
    var isNoteOpen by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .height(spacing.recordItemHeight)
            .background(
                CategoryWithColor
                    .fromString(recordItem.categoryColor)
                    .getColor()
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = spacing.spaceExtraSmall),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = spacing.spaceSmall, end = spacing.spaceMedium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (showRecordPageSettingItem.showCategory) {
                        CategoryDisplay(
                            category = UiCategory.byType(recordItem.categoryType),
                            textStyle = MaterialTheme.typography.titleSmall,
                            textFontWeight = FontWeight.Bold,
                            color = TextColorBlack,
                            paddingValue = 0.dp,
                        )
                    }
                    if (showRecordPageSettingItem.showCategoryName) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = spacing.spaceSmall),
                            text = recordItem.categoryName,
                            style = MaterialTheme.typography.titleSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = TextColorBlack
                        )
                    }
                }
                if (showRecordPageSettingItem.showRate) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = spacing.spaceSmall),
                        text = recordItem.categoryRate + "/hr",
                        style = MaterialTheme.typography.titleSmall,
                        color = TextColorBlack
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .height(0.25.dp)
                    .padding(horizontal = spacing.spaceMediumLarge)
                    .background(TextColorBlack)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceSmall),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconWithText(
                    drawableRes = R.drawable.start_time,
                    title = recordItem.startTime.displayAsTime(showRecordPageSettingItem.timeFormatAMPM)
                )
                Column {
                    IconWithText(
                        drawableRes = R.drawable.end_time,
                        title = recordItem.endTime.displayAsTime(showRecordPageSettingItem.timeFormatAMPM)
                    )
                }
                if (showRecordPageSettingItem.showUnit) {
                    Text(
                        text = recordItem.totalTime + " hrs",
                        style = MaterialTheme.typography.titleSmall,
                        color = TextColorBlack
                    )
                }
                Row(
                    modifier = Modifier.padding(end = if (recordItem.isPaid && showRecordPageSettingItem.showPaidCheck) 0.dp else spacing.categoryIconSize)
                ) {
                    if (showRecordPageSettingItem.showTotalAmount) {
                        IconWithText(
                            drawableRes = R.drawable.earning,
                            title = recordItem.totalAmount
                        )
                    }
                    if (recordItem.isPaid && showRecordPageSettingItem.showPaidCheck) {
                        Icon(
                            modifier = Modifier.size(spacing.categoryIconSize),
                            painter = painterResource(id = R.drawable.paid_check),
                            contentDescription = "",
                            tint = Color.Green
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacing.spaceExtraSmall),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (recordItem.note.isNotEmpty()) {
                    NotesPopup(
                        notes = recordItem.note,
                        expanded = isNoteOpen,
                        onExpand = { isNoteOpen = true },
                        onDismiss = { isNoteOpen = false },
                        modifier = Modifier.padding(horizontal = spacing.spaceLarge)
                    )
                }
                Text(
                    text = "Updated: " + recordItem.lastUpdated,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(end = spacing.spaceSmall),
                    color = TextColorBlack
                )
            }
        }

    }
}
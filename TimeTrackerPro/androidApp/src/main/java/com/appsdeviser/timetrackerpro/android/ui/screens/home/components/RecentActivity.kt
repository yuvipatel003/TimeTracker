package com.appsdeviser.timetrackerpro.android.ui.screens.home.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.components.SingleRecordView
import com.appsdeviser.timetrackerpro.android.ui.core.displayAsDate
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack
import com.appsdeviser.timetrackerpro.android.ui.screens.onboarding.components.ActionButton
import com.appsdeviser.tracker.presentation.record.view.UIRecordItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecentActivity(
    modifier: Modifier = Modifier,
    onEdit: (recordItem: UIRecordItem) -> Unit,
    onViewAllRecord: () -> Unit,
    listOfGroupByRecords: List<Pair<String, List<UIRecordItem>>>,
    showRecordPageSettingItem: ShowRecordPageSettingItem
) {
    val spacing = LocalSpacing.current
    val lazyState = rememberLazyListState()
    LazyColumn(
        state = lazyState,
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = spacing.spaceExtraSmall,
                start = spacing.spaceExtraSmall,
                end = spacing.spaceExtraSmall,
                bottom = spacing.settingsDateWidth + spacing.spaceMedium + spacing.spaceExtraSmall
            )
    ) {
        items(listOfGroupByRecords.size) { index ->
            val pair = listOfGroupByRecords[index]
            Text(
                text = pair.first.displayAsDate(showRecordPageSettingItem.dateFormat),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(
                        start = spacing.spaceSmallMedium,
                        end = spacing.spaceSmallMedium,
                        bottom = spacing.spaceSmall,
                        top = spacing.spaceSmallMedium
                    )
            )

            pair.second.forEach { recordItem ->
                SingleRecordView(
                    modifier = Modifier
                        .padding(horizontal = spacing.spaceExtraSmall)
                        .clip(RoundedCornerShape(spacing.spaceSmall))
                        .combinedClickable(
                            onClick = {},
                            onLongClick = {
                                onEdit(recordItem)
                            }
                        ),
                    recordItem = recordItem,
                    showRecordPageSettingItem = showRecordPageSettingItem
                )
                VerticalDivider(
                    modifier = Modifier
                        .height(1.dp)
                        .background(TextColorBlack)
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceSmallMedium))
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.categoryItemHeight),
                text = stringResource(R.string.view_all_records),
                onClick = { onViewAllRecord() })
        }
    }
}
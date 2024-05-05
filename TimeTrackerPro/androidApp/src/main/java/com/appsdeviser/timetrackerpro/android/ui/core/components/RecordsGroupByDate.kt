package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.appsdeviser.core_db.domain.showrecordpage.ShowRecordPageSettingItem
import com.appsdeviser.timetrackerpro.android.ui.core.displayAsDate
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack
import com.appsdeviser.tracker.presentation.record.view.UIRecordItem

@Composable
fun RecordsGroupByDate(
    modifier: Modifier = Modifier,
    onEdit: (recordItem: UIRecordItem) -> Unit,
    onRemove: (recordItem: UIRecordItem) -> Unit,
    listOfGroupByRecords: List<Pair<String, List<UIRecordItem>>>,
    showRecordPageSettingItem: ShowRecordPageSettingItem
) {
    val spacing = LocalSpacing.current
    val lazyState = rememberLazyListState()
    Column {
        Box {
            LazyColumn(
                state = lazyState,
                modifier = modifier
                    .wrapContentHeight()
                    .padding(
                        top = spacing.spaceExtraSmall,
                        start = spacing.spaceExtraSmall,
                        end = spacing.spaceExtraSmall,
                        bottom = spacing.spaceSmall
                    )
            ) {
                items(
                    items = listOfGroupByRecords
                ) { pair ->
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
                        SwipeableContentView(
                            modifier = Modifier
                                .clip(RoundedCornerShape(spacing.spaceSmall)),
                            height = spacing.recordItemHeight,
                            onEdit = { onEdit(recordItem) },
                            onRemove = { onRemove(recordItem) },
                            onAddNewRecord = { onEdit(recordItem) },
                            content = {
                                SingleRecordView(
                                    recordItem = recordItem,
                                    showRecordPageSettingItem = showRecordPageSettingItem
                                )
                            }
                        )
                        VerticalDivider(
                            modifier = Modifier
                                .height(1.dp)
                                .background(TextColorBlack)
                        )
                    }
                }
            }

            ScrollToTopButton(
                state = lazyState,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = spacing.spaceLarge, end = spacing.spaceSmall)
                    .size(spacing.scrollToTopButtonSize)
            )
        }
    }
}
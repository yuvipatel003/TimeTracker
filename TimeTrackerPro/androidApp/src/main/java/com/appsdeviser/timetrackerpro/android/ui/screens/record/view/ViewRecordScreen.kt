package com.appsdeviser.timetrackerpro.android.ui.screens.record.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.components.RecordsGroupByDate
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
    ) {
        TitleBar(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.view_records),
            onBackClick = onBackClick
        )
        if(state.showRefresh) {
            Icon(
                modifier = Modifier
                    .padding(spacing.spaceSmall)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(spacing.spaceSmall)
                    .clickable {
                        onEvent(ViewRecordEvent.RefreshRecords)
                    }
                    .size(spacing.smallLanguageIcon),
                imageVector = Icons.Default.Refresh,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        RecordsGroupByDate(
            modifier = Modifier,
            onEdit = {
                onEvent(ViewRecordEvent.UpdateRefresh)
                onEvent(ViewRecordEvent.SelectRecord(it.id ?: -1))
            },
            onRemove = {

            },
            listOfGroupByRecords = state.listOfRecords,
            showRecordPageSettingItem = state.showRecordPageSettingItem
        )
    }
}
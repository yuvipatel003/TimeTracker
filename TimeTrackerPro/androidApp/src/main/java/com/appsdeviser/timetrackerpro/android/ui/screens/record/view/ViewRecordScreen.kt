package com.appsdeviser.timetrackerpro.android.ui.screens.record.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.appsdeviser.timetrackerpro.android.R
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
    ) {
        TitleBar(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.view_records),
            onBackClick = onBackClick
        )

        LazyColumn {
            items(
                items = state.listOfRecords,
                key = {
                    it.id ?: -1
                }
            ) {record ->
                Text(
                    text = record.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent(ViewRecordEvent.SelectRecord(recordId = record.id ?: -1))
                        }
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
            }
        }
    }
}
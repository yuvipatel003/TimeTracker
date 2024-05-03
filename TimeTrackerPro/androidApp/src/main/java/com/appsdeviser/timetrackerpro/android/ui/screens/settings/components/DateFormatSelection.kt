package com.appsdeviser.timetrackerpro.android.ui.screens.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.appsdeviser.timetrackerpro.android.ui.core.formatDate
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import java.util.Date

@Composable
fun DateFormatSelection(
    listOfDateFormat: List<String>,
    currentSelection: String,
    title: String = "",
    onChange: (dateFormat: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    var isSelecting by remember { mutableStateOf(false) }
    var selectedOptionIndex by remember {
        mutableStateOf(
            listOfDateFormat.indexOf(currentSelection)
        )
    }
    val currentDate = remember { Date() }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.Top),
            style = MaterialTheme.typography.bodyMedium
        )

        if (isSelecting) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.width(spacing.settingsDateWidth)
            ) {
                listOfDateFormat.forEachIndexed { index, option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(spacing.spaceLarge)
                            .clickable {
                                selectedOptionIndex = index
                                onChange(option)
                                isSelecting = false
                            }
                    ) {
                        RadioButton(
                            selected = index == selectedOptionIndex,
                            onClick = {
                                selectedOptionIndex = index
                                onChange(option)
                                isSelecting = false
                            }
                        )
                        Text(
                            text = formatDate(currentDate, option),
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                        )
                    }
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .clickable {
                        isSelecting = true
                    }
                    .padding(horizontal = spacing.spaceSmall),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = formatDate(currentDate, currentSelection),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(end = spacing.spaceMedium)
                )
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = ""
                )
            }
        }

    }
}
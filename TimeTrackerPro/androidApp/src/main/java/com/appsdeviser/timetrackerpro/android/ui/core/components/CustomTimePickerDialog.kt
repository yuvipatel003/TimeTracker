package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightPrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.PrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack
import com.appsdeviser.timetrackerpro.android.ui.core.theme.onPrimaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePickerDialog(
    onAccept: (String) -> Unit,
    onCancel: () -> Unit
) {
    val state = rememberTimePickerState()

    DatePickerDialog(
        onDismissRequest = { },
        confirmButton = {
            Button(
                onClick = {
                    onAccept(state.hour.toString() + ":" + state.minute.toString() + ":00")
                }
            ) {
                Text(stringResource(id = R.string.accept))
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text(stringResource(id = R.string.cancel))
            }
        }
    ) {
        TimePicker(
            state = state,
            colors = TimePickerColors(
                clockDialColor = LightPrimaryColor,
                selectorColor = PrimaryColor,
                containerColor = MaterialTheme.colorScheme.background,
                periodSelectorBorderColor = PrimaryColor,
                clockDialSelectedContentColor = onPrimaryColor,
                clockDialUnselectedContentColor = TextColorBlack,
                periodSelectorSelectedContainerColor = PrimaryColor,
                periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.background,
                periodSelectorSelectedContentColor = onPrimaryColor,
                periodSelectorUnselectedContentColor =onPrimaryColor,
                timeSelectorSelectedContainerColor = PrimaryColor,
                timeSelectorUnselectedContainerColor =MaterialTheme.colorScheme.background,
                timeSelectorSelectedContentColor = onPrimaryColor,
                timeSelectorUnselectedContentColor = onPrimaryColor
            )
        )
    }
}
package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.DEFAULT_DB_DATE_FORMAT
import com.appsdeviser.timetrackerpro.android.ui.core.displayAsDate
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Composable
fun CustomDatePicker(
    onSelect: (date: String) -> Unit,
    isStartDate: Boolean,
    selectedDate: String
) {
    val date = remember { mutableStateOf(LocalDate.now()) }
    val isOpen = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = { isOpen.value = true }
        ) {
            Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = stringResource(R.string.select_date)
            )
        }
    }

    if (isOpen.value) {
        CustomDatePickerDialog(
            onAccept = {
                isOpen.value = false // close dialog
                if (it != null) { // Set the date
                    date.value = Instant
                        .ofEpochMilli(it)
                        .atZone(ZoneId.of("UTC"))
                        .toLocalDate()
                    onSelect(date.value.toString().displayAsDate(DEFAULT_DB_DATE_FORMAT))
                }
            },
            onCancel = {
                isOpen.value = false //close dialog
            },
            selectedDate = selectedDate,
            isStartDate = isStartDate
        )
    }
}

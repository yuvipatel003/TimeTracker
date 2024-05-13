package com.appsdeviser.timetrackerpro.android.ui.core.components

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.DEFAULT_DB_DATE_FORMAT
import com.appsdeviser.timetrackerpro.android.ui.core.ONE_DAY_MILLIS
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightPrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.PrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.onPrimaryColor
import java.text.SimpleDateFormat
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    onAccept: (Long?) -> Unit,
    onCancel: () -> Unit,
    isStartDate: Boolean,
    selectedDate: String
) {
    val state = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            @SuppressLint("SimpleDateFormat")
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return if(selectedDate.isEmpty()) {
                    true
                } else {
                    val sdf = SimpleDateFormat(DEFAULT_DB_DATE_FORMAT)
                    val date: Date? = sdf.parse(selectedDate)
                    val millis: Long = date?.time ?: -1L
                    if (isStartDate) {
                        utcTimeMillis < millis
                    } else {
                        utcTimeMillis + ONE_DAY_MILLIS > millis
                    }
                }
            }
        }
    )

    DatePickerDialog(
        onDismissRequest = { },
        confirmButton = {
            Button(onClick = { onAccept(state.selectedDateMillis) }) {
                Text(stringResource(R.string.accept))
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text(stringResource(R.string.cancel))
            }
        },
    ) {
        DatePicker(
            state = state,
            colors = DatePickerColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = LightPrimaryColor,
                headlineContentColor = MaterialTheme.colorScheme.onBackground,
                weekdayContentColor = MaterialTheme.colorScheme.onBackground,
                subheadContentColor =LightPrimaryColor,
                navigationContentColor = MaterialTheme.colorScheme.onBackground,
                yearContentColor =LightPrimaryColor,
                disabledYearContentColor = Color.Gray,
                currentYearContentColor =LightPrimaryColor,
                selectedYearContentColor =LightPrimaryColor,
                disabledSelectedYearContentColor = Color.Gray,
                selectedYearContainerColor =LightPrimaryColor,
                disabledSelectedYearContainerColor = Color.Gray,
                dayContentColor = LightPrimaryColor,
                disabledDayContentColor = Color.Gray,
                selectedDayContentColor = onPrimaryColor,
                disabledSelectedDayContentColor = Color.Gray,
                selectedDayContainerColor = PrimaryColor,
                disabledSelectedDayContainerColor = Color.Gray,
                todayContentColor = LightPrimaryColor,
                todayDateBorderColor = LightPrimaryColor,
                dayInSelectionRangeContainerColor = LightPrimaryColor,
                dayInSelectionRangeContentColor = LightPrimaryColor,
                dividerColor = LightPrimaryColor,
                dateTextFieldColors = TextFieldDefaults.colors()
            )
        )
    }
}
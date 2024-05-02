package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreTime
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.displayAsTime

@Composable
fun CustomTimePicker(
    onSelect: (time: String) -> Unit
) {
    val isOpen = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = { isOpen.value = true }
        ) {
            Icon(
                imageVector = Icons.Default.MoreTime,
                contentDescription = stringResource(R.string.time)
            )
        }
    }

    if (isOpen.value) {
        CustomTimePickerDialog(
            onAccept = {
                isOpen.value = false // close dialog
                onSelect(it.displayAsTime(false))
            },
            onCancel = {
                isOpen.value = false //close dialog
            }
        )
    }
}

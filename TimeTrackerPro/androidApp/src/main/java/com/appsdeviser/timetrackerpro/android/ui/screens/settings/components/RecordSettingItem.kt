package com.appsdeviser.timetrackerpro.android.ui.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun RecordSettingItem(
    title: String = "",
    isEnabled: Boolean = false,
    onChange: (enabled: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {

    var isChecked by remember { mutableStateOf(isEnabled) }

    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = textStyle
        )

        Switch(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
                onChange(it)
            }
        )

    }
}
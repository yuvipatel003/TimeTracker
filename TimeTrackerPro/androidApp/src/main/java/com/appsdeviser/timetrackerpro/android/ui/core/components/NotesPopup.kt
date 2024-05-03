package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack

@Composable
fun NotesPopup(
    notes: String,
    expanded: Boolean,
    onExpand: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Column {
        Row(
            modifier = Modifier
                .clickable(onClick = { onExpand() })
                .padding(horizontal = spacing.spaceExtraSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .clickable {
                        onExpand()
                    },
                imageVector = Icons.Outlined.Info,
                contentDescription = "",
                tint = TextColorBlack
            )
        }
        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onDismiss() }
        ) {
            Box(
                modifier = Modifier
            ) {
                Text(
                    text = notes,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(
                            horizontal = spacing.spaceSmallMedium,
                            vertical = spacing.spaceSmall
                        )
                )
            }
        }
    }
}
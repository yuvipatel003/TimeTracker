package com.appsdeviser.timetrackerpro.android.ui.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightGreen
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightPrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LightRed
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack

@Composable
fun SettingsText(
    modifier: Modifier = Modifier,
    value: String = "",
    imageVector: ImageVector,
    type: String = "",
    onUpdated: (updatedValue: String) -> Unit
) {
    val spacing = LocalSpacing.current
    val isEditMode = remember {
        mutableStateOf(false)
    }
    var currentValue by remember { mutableStateOf(value) }

    if (isEditMode.value) {
        Column(
            modifier
                .border(
                    width = 0.2.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(spacing.spaceMedium)
                )
                .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = currentValue,
                onValueChange = {
                    currentValue = it

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(spacing.spaceMedium)),
                leadingIcon = {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = null
                    )
                },
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = TextColorBlack
                )
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .size(height = spacing.spaceLarge, width = spacing.spaceExtraLarge)
                        .background(
                            color = LightGreen,
                            shape = RoundedCornerShape(spacing.spaceMedium)
                        )
                        .clickable {
                            isEditMode.value = false
                            onUpdated(currentValue)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "",
                        tint = TextColorBlack
                    )
                }
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                Box(
                    modifier = Modifier
                        .size(height = spacing.spaceLarge, width = spacing.spaceExtraLarge)
                        .background(
                            color = LightRed,
                            shape = RoundedCornerShape(spacing.spaceMedium)
                        )
                        .clickable {
                            isEditMode.value = false
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "",
                        tint = TextColorBlack
                    )
                }

            }
        }
    } else {
        Row(
            modifier = modifier
                .border(
                    width = 0.2.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(spacing.spaceMedium)
                )
                .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmallMedium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                if (type.isNotEmpty()) {
                    Text(
                        text = type,
                        color = LightPrimaryColor,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
                }
                Text(
                    text = value,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "",
                modifier = Modifier
                    .padding(spacing.spaceExtraSmall)
                    .clickable {
                        isEditMode.value = true
                        currentValue = value
                    }
            )
        }
    }
}
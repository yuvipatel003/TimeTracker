package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.PrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.onPrimaryColor

@Composable
fun SingleFloatingActionButton(
    onClick: () -> Unit,
    imgVector: ImageVector,
    title: String
) {
    val spacing = LocalSpacing.current

    FloatingActionButton(
        onClick = {
            onClick()
        },
        containerColor = PrimaryColor,
        contentColor = onPrimaryColor,
        modifier = Modifier.clip(CircleShape)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(
                horizontal = spacing.spaceSmallMedium,
                vertical = spacing.spaceExtraSmall
            )
        ) {
            Icon(
                imageVector = imgVector,
                contentDescription = title
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = spacing.spaceExtraSmall)
            )
        }
    }
}
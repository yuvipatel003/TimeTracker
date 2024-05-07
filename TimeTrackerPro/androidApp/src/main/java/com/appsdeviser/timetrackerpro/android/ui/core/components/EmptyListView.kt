package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing

@Composable
fun EmptyListView(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    imageVector: ImageVector,
    imageSize: Dp,
    title: String = "",
    titleStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    showContainerBorder: Boolean = false
) {

    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(spacing.spaceExtraSmall)
            .border(
                1.dp,
                if (showContainerBorder) contentColor else Color.Transparent,
                RoundedCornerShape(spacing.spaceSmall)
            )
            .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = imageVector,
            contentDescription = "",
            modifier = Modifier.size(imageSize),
            colorFilter = ColorFilter.tint(color = contentColor)
        )
        Spacer(modifier = Modifier.width(spacing.spaceSmallMedium))
        Text(
            text = title,
            style = titleStyle,
            color = contentColor
        )
    }

}
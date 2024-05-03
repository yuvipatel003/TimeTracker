package com.appsdeviser.timetrackerpro.android.ui.screens.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appsdeviser.timetrackerpro.android.R
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.PrimaryColor
import com.appsdeviser.timetrackerpro.android.ui.core.theme.onPrimaryColor

@Composable
fun FloatingActionButtonItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier.height(55.dp),
    @DrawableRes
    imageRes: Int = -1,
    title: String = ""
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .padding(spacing.spaceExtraSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        FloatingActionButton(
            onClick = onClick,
            containerColor = PrimaryColor,
            contentColor = onPrimaryColor,
            modifier = Modifier.clip(RoundedCornerShape(spacing.spaceMediumLarge))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(
                    horizontal = spacing.spaceSmallMedium,
                    vertical = spacing.spaceExtraSmall
                )
            ) {
                if (title.isNotEmpty()) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = spacing.spaceExtraSmall)
                    )
                }
                Icon(
                    painter = painterResource(id = imageRes),
                    contentDescription = stringResource(id = R.string.floating_button) + title,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}
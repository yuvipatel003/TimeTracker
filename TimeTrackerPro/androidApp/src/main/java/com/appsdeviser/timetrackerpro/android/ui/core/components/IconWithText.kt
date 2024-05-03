package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import com.appsdeviser.timetrackerpro.android.ui.core.theme.TextColorBlack

@Composable
fun IconWithText(
    modifier: Modifier = Modifier,
    @DrawableRes drawableRes: Int,
    title: String,
    isHorizontal: Boolean = true
) {
    val spacing = LocalSpacing.current

    if (isHorizontal) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = drawableRes),
                contentDescription = "",
                modifier = modifier.size(spacing.titleBarIconSize),
                colorFilter = ColorFilter.tint(color = TextColorBlack)
            )
            Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = TextColorBlack
            )
        }
    } else {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = drawableRes),
                contentDescription = "",
                modifier = modifier.size(spacing.titleBarIconSize),
                colorFilter = ColorFilter.tint(color = TextColorBlack)
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = TextColorBlack
            )
        }
    }

}
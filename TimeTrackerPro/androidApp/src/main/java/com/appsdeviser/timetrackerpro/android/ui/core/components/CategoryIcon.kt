package com.appsdeviser.timetrackerpro.android.ui.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.appsdeviser.core_common.presentation.UiCategory
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing

@Composable
fun CategoryIcon(
    category: UiCategory,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current

    Image(
        painter = painterResource(id = category.drawableRes),
        contentDescription = category.categoryType,
        modifier = modifier.size(spacing.smallLanguageIcon),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
    )
}
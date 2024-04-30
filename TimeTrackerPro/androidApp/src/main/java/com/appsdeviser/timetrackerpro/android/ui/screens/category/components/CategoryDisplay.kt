package com.appsdeviser.timetrackerpro.android.ui.screens.category.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.appsdeviser.core_common.presentation.UiCategory
import com.appsdeviser.timetrackerpro.android.ui.core.components.CategoryIcon
import com.appsdeviser.timetrackerpro.android.ui.core.theme.LocalSpacing
import java.util.Locale

@Composable
fun CategoryDisplay(
    category: UiCategory,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textFontWeight: FontWeight = FontWeight.Normal
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .padding(horizontal = spacing.spaceSmall, vertical = spacing.spaceSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CategoryIcon(category = category)
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Text(
            text = category.categoryType.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            },
            style = textStyle,
            fontWeight = textFontWeight
        )
    }
}
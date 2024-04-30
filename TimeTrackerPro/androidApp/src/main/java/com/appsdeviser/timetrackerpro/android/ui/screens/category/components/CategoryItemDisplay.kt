package com.appsdeviser.timetrackerpro.android.ui.screens.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.appsdeviser.timetrackerpro.android.ui.core.CategoryWithColor
import com.appsdeviser.tracker.domain.category.CategoryItem

@Composable
fun CategoryItemDisplay(
    categoryItem: CategoryItem
) {
    Box(
        modifier = Modifier
            .background(CategoryWithColor.fromString(categoryItem.color).getColor())
    ) {
        Text(text = categoryItem.toString(), fontSize = 26.sp)
    }
}
package com.appsdeviser.timetrackerpro.android.ui.screens.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CategoryColorCircle(
    color: Color,
    modifier: Modifier = Modifier,
    size: Dp,
    isSelected: Boolean
) {
    Box(
        modifier = modifier
            .size(size)
            .border(
                width = if (isSelected) 3.dp else 0.75.dp,
                color = Color.Black,
                shape = CircleShape
            )
    ) {
        ColorCircle(
            color = color,
            modifier = Modifier.matchParentSize(),
            size = size - 4.dp
        )
    }
}


@Composable
fun ColorCircle(
    color: Color,
    modifier: Modifier = Modifier,
    size: Dp
) {
    Box(
        modifier = modifier
            .size(size)
            .background(color, shape = CircleShape)
    )
}
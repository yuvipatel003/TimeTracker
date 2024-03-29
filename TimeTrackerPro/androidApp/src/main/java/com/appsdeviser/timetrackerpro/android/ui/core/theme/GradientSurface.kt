package com.appsdeviser.timetrackerpro.android.ui.core.theme

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.gradientSurface(): Modifier = composed {
    if (isSystemInDarkTheme()) {
        Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF23262E),
                        Color(0xFF212329)
                    )
                )
            )
    } else {
        Modifier
            .background(MaterialTheme.colorScheme.surface)
    }
}

@Composable
fun Modifier.changingGradientBackground(): Modifier = composed {
    val colorAnimation = rememberInfiniteTransition(label = "")
    val color by colorAnimation.animateColor(
        initialValue = IconColor,
        targetValue = MaterialTheme.colorScheme.primary,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    this.then(
        Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    color,
                    MaterialTheme.colorScheme.primary
                )
            )
        )
    )
}

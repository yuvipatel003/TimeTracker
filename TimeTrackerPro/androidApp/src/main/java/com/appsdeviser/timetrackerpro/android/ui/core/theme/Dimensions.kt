package com.appsdeviser.timetrackerpro.android.ui.core.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val default: Dp = 0.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceSmallMedium: Dp = 12.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceMediumLarge: Dp = 24.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceExtraLarge: Dp = 64.dp,
    val splashClockSize: Dp = 200.dp,
    val titleBarIconSize: Dp = 25.dp,
    val notificationImageSize: Dp = 50.dp,
    val settingsDateWidth: Dp = 150.dp,
    val smallLanguageIcon: Dp = 30.dp,
    val categoryItemHeight: Dp = 80.dp,
    val categoryIconSize: Dp = 25.dp,
    val recordItemHeight: Dp = 120.dp,
    val scrollToTopButtonSize: Dp = 40.dp
)

data class DialogDimensions(
    val width: Dp = 350.dp
)

val LocalSpacing = compositionLocalOf { Dimensions() }
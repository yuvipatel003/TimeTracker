package com.appsdeviser.timetrackerpro.android.ui.core.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.appsdeviser.timetrackerpro.presentation.Colors

val PrimaryColor = Color(Colors.PrimaryColorAppsDeviser)
val IconColor = Color(Colors.PrimaryAppsDeviser)
val DarkTextColor = Color(Colors.secondaryColorAppsDeviser)
val LightBlue = Color(Colors.LightBlue)
val LightBlueGrey = Color(Colors.LightBlueGrey)
val DarkGrey = Color(Colors.DarkGrey)

val lightColors = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = LightBlueGrey,
    background = LightBlue,
    onBackground = DarkTextColor,
    surface = LightBlueGrey,
    onSurface = DarkTextColor
)

val darkColors = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = LightBlueGrey,
    background = DarkGrey,
    onBackground = LightBlueGrey,
    surface = DarkGrey,
    onSurface = LightBlueGrey
)
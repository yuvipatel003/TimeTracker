package com.appsdeviser.timetrackerpro.android.ui.core.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.appsdeviser.timetrackerpro.presentation.Colors

val PrimaryColor = Color(Colors.PrimaryColor)
val onPrimaryColor = Color(Colors.OnPrimaryColor)
val IconColor = Color(Colors.PrimaryColor)
val DarkGreyBackground = Color(Colors.DarkGreyBackground)
val OffWhiteBackground = Color(Colors.OffWhiteBackground)
val TextColorBlack = Color(Colors.TextColorBlack)
val TextColorWhite = Color(Colors.TextColorWhite)

val LightPrimaryColor = Color(Colors.LightPrimaryColor)
val LightBlue = Color(Colors.LightBlue)
val LightGreen = Color(Colors.LightGreen)
val LightRed = Color(Colors.LightRed)
val LightPink = Color(Colors.LightPink)
val LightOrange = Color(Colors.LightOrange)
val LightViolet = Color(Colors.LightViolet)
val LightYellow = Color(Colors.LightYellow)
val LightIndigo = Color(Colors.LightIndigo)

val lightColors = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = onPrimaryColor,
    background = OffWhiteBackground,
    onBackground = TextColorBlack,
    surface = OffWhiteBackground,
    onSurface = TextColorBlack
)

val darkColors = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = onPrimaryColor,
    background = DarkGreyBackground,
    onBackground = TextColorWhite,
    surface = DarkGreyBackground,
    onSurface = TextColorWhite
)
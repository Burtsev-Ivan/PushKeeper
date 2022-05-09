package ru.burtsev.push_keeper.presentation.theme

import androidx.compose.ui.graphics.Color

data class PushKeeperColors(
    val backgroundSurface: Color,
    val primaryColor: Color,
)

val lightColor = PushKeeperColors(
    backgroundSurface = Color(0xFF8F2121),
    primaryColor = Color(0xFF1B927A),
)

val darkColor = PushKeeperColors(
    backgroundSurface = Color(0xFF5E2416),
    primaryColor = Color(0xFF090D2B),
)


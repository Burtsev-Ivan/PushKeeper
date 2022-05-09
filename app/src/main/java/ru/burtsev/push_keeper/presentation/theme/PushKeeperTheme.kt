package ru.burtsev.push_keeper.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun PushKeeperTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) lightColor else darkColor
    CompositionLocalProvider(
        LocalPushKeeperColors provides colors,
        content = content,
    )
}

object PushKeeperThemee {
    val colors: PushKeeperColors
        @Composable
        @ReadOnlyComposable
        get() = LocalPushKeeperColors.current

}

val LocalPushKeeperColors = staticCompositionLocalOf<PushKeeperColors> {
    error("No colors provided")
}

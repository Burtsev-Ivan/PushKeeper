package ru.burtsev.push_keeper.presentation.navigation

import android.os.Bundle

sealed class Screen(
    val route: String,
    val args: Bundle? = null,
) {
    object Main : Screen("main")
}

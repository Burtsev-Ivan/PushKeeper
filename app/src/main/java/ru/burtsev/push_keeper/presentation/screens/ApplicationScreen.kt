package ru.burtsev.push_keeper.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.burtsev.push_keeper.presentation.navigation.Screen
import ru.burtsev.push_keeper.presentation.screens.filter.app.FilterAppScreen
import ru.burtsev.push_keeper.presentation.screens.notifications.NotificationsScreen

@Composable
fun ApplicationScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) { NotificationsScreen(navController) }
        composable(Screen.Filter.route) { FilterAppScreen(navController) }
    }
}


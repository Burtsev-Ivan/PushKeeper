package ru.burtsev.push_keeper.presentation.screens.notification

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import ru.burtsev.push_keeper.presentation.MainViewModel

import ru.burtsev.push_keeper.presentation.di.koinViewModel

@Composable
fun NotificationScreen(
    navController: NavHostController,
    viewModel: MainViewModel = koinViewModel(),
) {

    val viewState = viewModel.notificationLiveData.observeAsState()

    Text(text = "Количество записей = " + viewState.value?.size)
//    Text(text = "Количество записей =5")

}



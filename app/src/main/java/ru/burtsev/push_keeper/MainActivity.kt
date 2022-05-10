package ru.burtsev.push_keeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.burtsev.push_keeper.presentation.screens.ApplicationScreen
import ru.burtsev.push_keeper.presentation.theme.PushKeeperTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PushKeeperTheme {
                ApplicationScreen()
            }
        }
    }

}

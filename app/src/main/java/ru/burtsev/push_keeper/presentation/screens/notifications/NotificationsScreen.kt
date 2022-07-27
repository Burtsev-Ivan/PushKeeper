package ru.burtsev.push_keeper.presentation.screens.notifications

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import ru.burtsev.push_keeper.R
import ru.burtsev.push_keeper.domain.model.notification.Notification
import ru.burtsev.push_keeper.presentation.common.AppImage
import ru.burtsev.push_keeper.presentation.di.koinViewModel
import ru.burtsev.push_keeper.presentation.navigation.Screen

@Composable
fun NotificationsScreen(
    navController: NavHostController,
    viewModel: NotificationsViewModel = koinViewModel(),
) {
    val lazyPagingItems = viewModel.notificationFlow.collectAsLazyPagingItems()
    Column {

        TopAppBar(
            title = { Text(text = "Уведомления") },
            navigationIcon = null,
            actions = {
                IconButton(onClick = { navController.navigate(route = Screen.CommonFilter.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "Filter"
                    )
                }

                IconButton(onClick = { navController.navigate(route = Screen.Filter.route) }) {
                    Icon(imageVector = Icons.Filled.Settings, contentDescription = "Filter")
                }
            },
            backgroundColor = Color.Transparent,
            contentColor = Color.Gray,
            elevation = 2.dp
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                items = lazyPagingItems,
                itemContent = { value ->
                    value?.let { NotificationCardItem(it) }
                }
            )
        }

    }
}

@Composable
fun NotificationCardItem(model: Notification) {

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Column(Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
            Row {
                AppImage(model.packages)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, 0.dp, 0.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = model.appName,
                            Modifier
                                .padding(8.dp, 0.dp, 8.dp, 0.dp)
                                .weight(1f)
                                .wrapContentSize(),
                            style = MaterialTheme.typography.subtitle2,
                        )
                        Text(text = model.time)
                    }

                    Text(text = model.title, Modifier.padding(8.dp, 8.dp, 0.dp, 0.dp))
                }
            }
            Divider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(text = model.text, Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp))

        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
fun NotificationCardItem_Preview() {
    NotificationCardItem(
        Notification(
            id = 1,
            packages = "dasadasdasd",
            appName = "Viber",
            title = "Илья Ковалев",
            text = "Поделился с вами записью",
            time = "10.05 12:12:01",
        )
    )
}
